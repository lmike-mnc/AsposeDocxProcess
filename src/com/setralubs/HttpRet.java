package com.setralubs;
import java.io.InputStream;

public class HttpRet {
    StringBuffer buf=new StringBuffer();
    InputStream is=null;
    String contentType="";
    String contentDisposition="";

    public long getContentLength() {
        return contentLength;
    }

    long contentLength=0;
    int status=-1;
    public String toString(){
        return buf.toString();
    }

    public int getStatus(){
        return status;
    }

    public InputStream getInputStream(){
        return this.is;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

}
