package com.vieeo.component.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientComponentUtils {

    public static HttpClient getHttpClient(){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        return httpClient;
    }


    public static List<NameValuePair> createParams(Map<String,String> params){
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

    public static String createEncodeGetParams(Map<String,String> params,String charset) throws UnsupportedEncodingException{
        if(params == null || params.size() <= 0) return null;
        StringBuilder result = new StringBuilder();
        Set<String> keys = params.keySet();
        Iterator<String> iter = keys.iterator();

        while(iter.hasNext()){
            String key = iter.next();
            String value = params.get(key);
            result.append(key).append("=").append(URLEncoder.encode(value, charset));
            if(iter.hasNext()) result.append("&");
        }
        return result.toString();
    }

    public static String createEncodeGetParams(Map<String,String> params) throws UnsupportedEncodingException{
    	return createEncodeGetParams(params,"UTF-8");
    }

    public static String createGetParams(Map<String,String> params) throws UnsupportedEncodingException{
        if(params == null || params.size() <= 0) return null;
        StringBuilder result = new StringBuilder();
        Set<String> keys = params.keySet();
        Iterator<String> iter = keys.iterator();

        while(iter.hasNext()){
            String key = iter.next();
            String value = params.get(key);
            result.append(key).append("=").append(value);
            if(iter.hasNext()) result.append("&");
        }
        return result.toString();
    }

    public static String createGetParamsWithSort(Map<String,String> params) throws UnsupportedEncodingException{
        if(params == null || params.size() <= 0) return null;
        StringBuilder result = new StringBuilder();
        Set<String> keys = params.keySet();
        List<String> listKey = new ArrayList<String>(keys);
        Collections.sort(listKey);
        for (int i=0;i<listKey.size();i++) {
        	String key = listKey.get(i);
            String value = params.get(key);
            result.append(key).append("=").append(value);
            if(i < listKey.size()-1) result.append("&");
		}
        return result.toString();
    }

    public static String createEncodeGetParamsWithSort(Map<String,String> params,String charset) throws UnsupportedEncodingException{
        if(params == null || params.size() <= 0) return null;
        StringBuilder result = new StringBuilder();
        Set<String> keys = params.keySet();
        List<String> listKey = new ArrayList<String>(keys);
        Collections.sort(listKey);
        for (int i=0;i<listKey.size();i++) {
        	String key = listKey.get(i);
            String value = params.get(key);
            result.append(key).append("=").append(URLEncoder.encode(value, charset));
            if(i < listKey.size()-1) result.append("&");
		}
        return result.toString();
    }

    public static String createEncodeGetParamsWithSort(Map<String,String> params) throws UnsupportedEncodingException{
    	return createEncodeGetParamsWithSort(params,"UTF-8");
    }

}
