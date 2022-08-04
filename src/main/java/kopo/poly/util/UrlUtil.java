package kopo.poly.util;

import com.amazonaws.services.cognitoidp.model.HttpHeader;
import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;



@Slf4j
public class UrlUtil {

    private String readAll(Reader rd) {
        log.info("UrlUtil: readAll 시작");
        StringBuilder sb =null;

        try {
            sb = new StringBuilder();
            int cp =0;

            while((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }

        } catch (Exception e) {
            log.info("readAll Exception : " + e.toString());
        }

        log.info("UrlUtil: readAll 종료");
        return sb.toString();
    }

    public String urlReadforString(String url) throws IOException {

        log.info("UrlUtil: urlReadforString 시작");
        log.info("UrlUtil: urlReadforString url :" + url);
        /**
         * url, Header, body
         */



        BufferedReader rd =null;
        InputStream is =null;


        String res ="";

        try {
            is = new URL(url).openStream();

            rd= new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            res = readAll(rd);

        }catch(Exception e) {
            log.info("urlReadforString Exception :" + e.toString());
        } finally {
            is.close();
            is = null;
            rd = null;
        }
        log.info("UrlUtil: urlReadforString 종료");

        return res ;
    }
}