package com.setralubs;

import com.aspose.words.Document;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.setralubs.DocxReplace.DEF_TARGET_EXT;

@WebServlet(name = "DocxInfo")
public class DocxInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                Map<String,String> mapTmp;
                Map<String,String[]> mapFields = new HashMap<>();
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
                        tblNames=obj.getTablesNames(doc);
                        mapFields=obj.getFields(doc, FindAndReplace.fieldRegex);
                        params.put("tables",tblNames);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

    }
}
