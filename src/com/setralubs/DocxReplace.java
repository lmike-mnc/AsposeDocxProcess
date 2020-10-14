package com.setralubs;

import com.aspose.words.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.setralubs.FindAndReplace.DATA_DIR;

public class DocxReplace extends javax.servlet.http.HttpServlet {
    //valid output types
    static List<String>validTypes=new ArrayList<>();
    static {
        validTypes.add("docx");
        validTypes.add("pdf");
        validTypes.add("html");
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> params = new HashMap<>();
            params.put("requestType",request.getMethod());
            //String sout = "{\"requestType\":\"" + request.getMethod() + "\"";
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> errs=new ArrayList<>();
            try {
                JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
                Map<String,String> mapTmp;
                Map<String,String> mapFields = null;
                Map<String,InputStream> mapSources = new HashMap<>();

                JsonNode tmpNode;
                String filePath;
                String fileType;
                boolean bOpen=false;
                JsonNode fieldsNode;

                tmpNode=jsonNode.get("filepath");
                filePath = tmpNode==null ? null : tmpNode.asText();
                tmpNode=jsonNode.get("filetype");
                fileType = tmpNode==null || !validTypes.contains(tmpNode.asText())?"pdf":tmpNode.asText();
                fieldsNode=jsonNode.get("fields");
                System.out.println("filepath: " + filePath);
                if (fieldsNode!=null){
                    mapFields = objectMapper.convertValue(fieldsNode, new TypeReference<Map<String, String>>(){});
                    System.out.println("fields:\n"+
                            mapFields.entrySet().stream()
                                    .map(e -> e.getKey() + "=" + e.getValue())
                                    .collect(Collectors.joining(", ", "{", "}"))
                    );
                }
                tmpNode=jsonNode.get("sources");
                if (tmpNode!=null){
                    mapTmp = objectMapper.convertValue(tmpNode, new TypeReference<Map<String, String>>(){});
                    mapTmp.entrySet().stream()
                            .filter(e->{
                                String ext=FilenameUtils.getExtension(e.getValue());
                                return ext.equalsIgnoreCase("doc") ||
                                        ext.equalsIgnoreCase("docx");
                            })
                            .forEach(e->{
                                try {
                                    System.out.println("replacement source path: "+e.getValue());
                                    mapSources.put(e.getKey(),new FileInputStream(e.getValue()));
                                } catch (FileNotFoundException fileNotFoundException) {
                                    errs.add(fileNotFoundException.getLocalizedMessage());
                                    fileNotFoundException.printStackTrace();
                                }
                            });
                }
                //replace and convert
                if(filePath!=null){
                    String ext=FilenameUtils.getExtension(filePath);
                    System.out.println("extension: "+ext);
                    params.put("filetype",fileType);
                    params.put("input-type",ext);
                    if (ext.equalsIgnoreCase("docx")) {
                        FindAndReplace obj = new FindAndReplace();
                        Document docTarget = obj.replaceWtables(new FileInputStream(filePath),
                                mapSources, mapFields);
                        File tmp = File.createTempFile("out", "."+fileType);
                        filePath = tmp.getAbsolutePath();
                        docTarget.save(filePath);
                        params.put("isConverted",true);
                        params.put("filepath",filePath);
                    }else {
                        errs.add("targetFileTypeError> input file type is wrong: "+ext);
                        params.put("isConverted",false);
                    }
                }else{
                    errs.add("targetFileError> input file path is null");
                    params.put("isConverted",false);
                }
                tmpNode =jsonNode.get("open");
                bOpen= tmpNode != null && tmpNode.asBoolean();
                if (filePath!=null && bOpen)Desktop.getDesktop().open(new File(filePath));
            } catch (IOException e) {
                errs.add("ioError> "+ e.getLocalizedMessage());
                e.printStackTrace();
            } catch( IllegalArgumentException e){
                errs.add("illegalArgumentError> "+ e.getLocalizedMessage());
                e.printStackTrace();
            } catch (Exception e) {
                errs.add("other error> "+e.getLocalizedMessage());
                e.printStackTrace();
            }
            if (!errs.isEmpty())params.put("errors",errs);
            String sout= new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(params);
            out.println(sout);
    } catch (IOException e) {
        e.printStackTrace();
    }

}

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> params = new HashMap<>();
            params.put("requestType",request.getMethod());
            //String sout = "{\"requestType\":\"" + request.getMethod() + "\"";
            try {
                Map<String,String> map= new HashMap<>();
                //<ADD4_POSITION> <ADD4_WHOM>
                map.put("<ADD4_POSITION>","Генерала");
                map.put("<ADD4_WHOM>","Кузнецова");
                File tmp=File.createTempFile("out",".pdf");
                String outName=tmp.getAbsolutePath();//DATA_DIR + "out.pdf";
                FindAndReplace obj=new FindAndReplace();
                Map<String, InputStream> mapSource=new HashMap<>();
                mapSource.put("add4_Грузополучатели"
                        ,obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "receivers.doc"));
                Document docTarget=obj.replaceWtables(obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "add4_test.docx")
                        ,mapSource
                        ,map);
                docTarget.save(outName);
                params.put("filepath",outName);
                params.put("os.mane",System.getProperty("os.name"));
                System.out.println("saved to: "+outName);
                System.out.println(System.getProperty("os.name"));
                Desktop.getDesktop().open(tmp);
                //FindAndReplace.main(null);
            } catch (Exception e) {
                //sout=sout + ",\"error\":\"" + e.getLocalizedMessage() +"\"";
                params.put("error",e.getLocalizedMessage());
                e.printStackTrace();
            }
            String sout = new ObjectMapper().writeValueAsString(params);
            out.println(sout);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
