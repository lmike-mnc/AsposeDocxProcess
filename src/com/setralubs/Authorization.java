package com.setralubs;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

public class Authorization {
    final org.slf4j.Logger LOG = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
    static final Object BaseURI = "http://";
    static final String dominoServer= "dom1.castrolcis.com:8080";
    static final String COOKIES_HEADER = "Set-Cookie";
    static final String httpuser="datatest";
    static final String httppasswd="glomglyz813";

    String  authorize() throws IOException {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        String url=BaseURI + dominoServer+"/names.nsf?Login&Username="
                + URLEncoder.encode(httpuser,"UTF-8")//httpuser.replaceAll(" ","%20")
                +"&Password="+httppasswd;
        URL object=new URL(url);
        HttpURLConnection conn = (HttpURLConnection) object.openConnection();
        String sCookies=null;
        int code=conn.getResponseCode();
        if (code==HttpURLConnection.HTTP_OK){
            String ret=result2String(conn);
            if (ret.startsWith("error!")){
                LOG.error("Is not authorized");
            }else{
                StringBuffer buf=new StringBuffer();
                int i=0;
                List<HttpCookie> list=cookieManager.getCookieStore().getCookies();
                for(HttpCookie s:list){
                    buf.append(s.toString());
                    i++;
                    if (i<list.size()) buf.append(";");
                }
                sCookies = buf.toString();
            }
            LOG.debug("result:\n"+ret);
        }
        return sCookies;
    }
    String result2String(HttpURLConnection conn){
        if (conn==null) return null;
        StringBuilder res = new StringBuilder();
        try {
            if (conn.getInputStream() != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    res.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            LOG.error("result reading IO error, "+e.getMessage());//e.printStackTrace();
        }
        return res.toString();
    }

}
