package com.setralubs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static com.setralubs.FindAndReplace.DATA_DIR;

/**
 * This utility class provides an abstraction layer for sending multipart HTTP
 * POST requests to a web server.
 * @author www.codejava.net
 *
 */
public class MultipartUtility {
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
    String sCookies=null;
    private boolean isError;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     * @param requestURL
     * @param charset
     * @throws IOException
     */
    public MultipartUtility(String requestURL, String charset, String token)
            throws IOException {
        this.charset = charset;

        // creates a unique boundary based on time stamp
        boundary = "===" + System.currentTimeMillis() + "===";

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Cookie",token);
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);
    }

    /**
     * Adds a form field to the request
     * @param name field name
     * @param value field value
     */
    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }

    /**
     * Adds a upload file section to the request
     * @param fieldName name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        String type=MIMEType.get(StringUtils.substringAfterLast(fileName,"."));
        writer.append(
                "Content-Type: "
                        + type)//URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);

        System.out.println("Content-type: "+type);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }

    /**
     * Adds a header field to the request.
     * @param name - name of the header field
     * @param value - value of the header field
     */
    public void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);
        writer.flush();
    }

    /**
     * Completes the request and receives response from the server.
     * @return a list of Strings as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
    public HttpRet finish() throws IOException {
        List<String> response = new ArrayList<String>();

        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();

        // checks server's status code first
        HttpRet ret=new HttpRet();
        ret.status = httpConn.getResponseCode();
        switch (ret.status) {
            case HttpURLConnection.HTTP_OK:
                // read from the urlconnection via the bufferedreader
                ret.is = httpConn.getInputStream();
                ret.contentDisposition=httpConn.getHeaderField("Content-Disposition");
                ret.contentType=httpConn.getContentType();
                ret.contentLength=httpConn.getContentLength();
                if (ret.contentType.contains("json") || httpConn.getContentType().contains("text")) {
                    System.out.println("text Content");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        ret.buf.append(line);
                        // System.out.println("Response: " + line);
                    }
                    bufferedReader.close();
                    isError = false;
                    Document doc = Jsoup.parse(ret.buf.toString());
                    Element body = doc.body();
                    List<Element> elements=body.getElementsByTag("input");
                    for(Element el:elements){
                        if (el.attributes().get("type").equalsIgnoreCase("password")){
                            isError=true;
                            //ret.buf.setLength(0);
                            //ret.buf.append(doc.outerHtml());
                            break;
                        }
                    }
                    if (isError){
                        Element el=body.getElementsByTag("h1").get(0);
                        ret.buf.setLength(0);
                        ret.buf.append((el.parent()).childNodes().get(1).toString());
                        ret.contentLength=0;
                    }else{
                        System.out.println("response:\n"+ret.buf.toString());
                    }
                }else{
                    System.out.println("Content:\n"
                            +ret.contentDisposition
                            +"\nsize: "+ret.contentLength);
                }
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                ret.buf.append("Interal server error while running");
                System.out.println(ret.buf.toString());
                break;
            default:
                ret.buf.append(httpConn.getResponseMessage());
                System.out.println("An error occurred: " + ret.status);
                System.out.println("Error message: " + ret.buf.toString());
                break;
        }
        if(isError){
            System.out.println(ret.buf.toString());
        }

/*
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }

*/
        return ret;
    }
static String testJson="{\n" +
        "  \"<ADD4_PHONE>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_NAME>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_KPP>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_WHO_POSITION>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_BNKNAME>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_LEGALADDR>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_POSITION>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_INN>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_SHORTNAME>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_CONTRDATE2>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_CONTRDATE1>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_BNKACC>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_ACC>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_WHO>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_BASIS>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_FAX>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_BNKBIC>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_CONTRNUMB2>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_CONTRNUMB1>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_OGRN>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_POSTADDR>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_STARTDATE>\" : [ \"\", \"\" ],\n" +
        "  \"<ADD4_WHOM>\" : [ \"\", \"\" ]\n" +
        "}";
    public Map<String, Object> getFieldsFromJson(String json) throws JsonProcessingException, IOException{
        System.out.println("Json to map conversion...");
        Map<String,Object> fldsMap = new HashMap<String, Object>();
        ObjectReader or = new ObjectMapper().reader().forType(fldsMap.getClass());
        fldsMap=or.readValue(json);
        System.out.println("Json to map conversion complete");
        return fldsMap;
    }

    public static void main(String[]args) throws IOException {
        Authorization authorization=new Authorization();
        String token=authorization.authorize();
        System.out.println("token: "+token);
        Fields fields=new Fields();
        fields
                .add("<SPA_POSITION>", new FieldData<String>("Генерал"))
                .add("<SPA_WHOM>", new FieldData<String>("Кузнецов"))
                .add("<SPA_BASIS>", new FieldData<String>("Устава"));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(fields);
        System.out.println(json);
        ObjectReader or = new ObjectMapper().reader().forType(Fields.class);
        Fields fields1 = or.readValue(json);
//System.out.println(fields1);
        Map<String,String[]> fldsMap = new HashMap<String, String[]>();
        or = new ObjectMapper().reader().forType(fldsMap.getClass());
        fldsMap=or.readValue(testJson);

        List <Map.Entry<String,String[]>> targetList= new ArrayList<Map.Entry<String, String[]>>(fldsMap.entrySet());
        //System.exit(0);
        //TrustAllCertificates.install();
        String findAndRepplace="http://mail1.castrolcis.com:8080/templates/pubdata.nsf/testAspose.xsp";
        MultipartUtility form=new MultipartUtility(findAndRepplace,"UTF-8", token);
        form.addFormField("routine","getFields");
        form.addFormField("<SPA_POSITION>","Генерал");
        form.addFormField("<SPA_WHOM>","Кузнецов");
        form.addFormField("<SPA_BASIS>","Устава");
        form.addFilePart("body", new File(DATA_DIR+"SpA_SupAgreement.docx"));
        HttpRet ret=form.finish();
        if (ret.contentLength>0 && ret.is!=null){
            if (ret.getContentType().contains("json")){
                Map<String,Object> map=form.getFieldsFromJson(ret.buf.toString());
                Iterator<Map.Entry<String, Object>> itr= map.entrySet().iterator();
                FieldConv fieldConv=new FieldConv();
                while (itr.hasNext()){
                    fieldConv.setEntry(itr.next());
                    System.out.println(fieldConv.getKey() +"->" + Arrays.toString(fieldConv.getValue()));
                }

            }else {
                String outName = DATA_DIR + "out.docx";
                FileUtils.copyInputStreamToFile(ret.is, new File(outName));
                Runtime.getRuntime().exec("gio open " + outName);
            }
        }
    }
}