package com.vieeo.component.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class BaseHttpClientComponent {

    protected HttpClient getHttpClient(){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        return httpClient;
    }


    protected List<NameValuePair> createParams(Map<String,String> params){
        if(params == null || params.size() <= 0) return null;
        List <NameValuePair> postParams = new ArrayList <NameValuePair>();

        Set<String> keys = params.keySet();
        Iterator<String> iter = keys.iterator();

        while(iter.hasNext()){
            String key = iter.next();
            String value = params.get(key);
            postParams.add(new BasicNameValuePair(key, value));
        }
        return postParams;
    }

    protected String createGetParams(Map<String,String> params) throws UnsupportedEncodingException{
        if(params == null || params.size() <= 0) return null;
        StringBuilder result = new StringBuilder("?");
        Set<String> keys = params.keySet();
        Iterator<String> iter = keys.iterator();

        while(iter.hasNext()){
            String key = iter.next();
            String value = params.get(key);
            result.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
            if(iter.hasNext()) result.append("&");
        }
        return result.toString();
    }

}
