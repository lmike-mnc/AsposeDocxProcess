package com.setralubs;

import com.aspose.words.Document;
import com.aspose.words.Table;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Authenticator;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.setralubs.DocxReplace.DEF_TARGET_EXT;

@WebServlet(name = "DocxInfo")
public class DocxInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out;
        try {
//https://docs.oracle.com/javaee/5/api/javax/servlet/ServletResponse.html#getWriter()
            //should be before getWriter()
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            Map<String, Object> params = new HashMap<>();
            params.put("requestType",request.getMethod());
            //String sout = "{\"requestType\":\"" + request.getMethod() + "\"";
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> errs=new ArrayList<>();
            try {
                JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
                Map<String,String[]> mapFields;
                List<String> tblNames;

                JsonNode tmpNode;
                String filePath;

                tmpNode=jsonNode.get("filepath");
                filePath = tmpNode==null ? null : tmpNode.asText();
                System.out.println("filepath: " + filePath);
                //replace and convert
                if(filePath!=null){
                    String ext=FilenameUtils.getExtension(filePath);
                    System.out.println("extension: "+ext);
                    params.put("input-type",ext);
                    //target doc checking
                    if (ext.equalsIgnoreCase(DEF_TARGET_EXT)) {
                        FindAndReplace obj = new FindAndReplace();
                        InputStream is=new FileInputStream(filePath);
                        Document doc=new Document(is);

                        mapFields=obj.getFields(doc, FindAndReplace.fieldRegex);
                        tmpNode=jsonNode.get("extract");
                        boolean bExport= tmpNode != null && tmpNode.asBoolean();
                        if (bExport){
                            Map<String, Table>map=obj.getTablesNamesMap(doc);
                            params.put("tablesPath",
                                    map.entrySet().stream()
                                    .collect(Collectors.toMap(
                                            Map.Entry::getKey,
                                            e -> {
                                                try {
                                                    Document tmpDoc=obj.extractTable(e.getValue());
                                                    File tmp= File.createTempFile("out","."+DEF_TARGET_EXT);
                                                    tmpDoc.save(tmp.getAbsolutePath());
                                                    return tmp.getAbsolutePath();
                                                } catch (Exception exception) {
                                                    String ret="->"+exception.getLocalizedMessage();
                                                    exception.printStackTrace();
                                                    return ret;
                                                }
                                            }))
                            );
                        }else{
                            tblNames=obj.getTablesNames(doc);
                            params.put("tables",tblNames);
                        }
                        params.put("fields",mapFields);
                    }else {
                        errs.add("targetFileTypeError> input file type is wrong: "+ext);
                    }
                }else{
                    errs.add("targetFileError> input file path is null");
                }
/*
                tmpNode =jsonNode.get("open");
                bOpen= tmpNode != null && tmpNode.asBoolean();
                if (filePath!=null && bOpen) Desktop.getDesktop().open(new File(filePath));
*/
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

    }
}
