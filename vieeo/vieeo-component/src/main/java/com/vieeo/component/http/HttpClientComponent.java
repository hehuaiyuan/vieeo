package com.vieeo.component.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientComponent {

	private static Logger logger = Logger.getLogger(HttpClientComponent.class);

	public String doPost(String url,Map<String,String> params) throws Exception{
		HttpClient client = getHttpClient();

		HttpPost post = new HttpPost(url);

		List<NameValuePair> postParams = createParams(params);
		if(CollectionUtils.isNotEmpty(postParams)) {
			post.setEntity(new UrlEncodedFormEntity(postParams,Consts.UTF_8));
		}

		//ResponseHandler<String> responseHandler = new BasicResponseHandler();

		HttpResponse resp = client.execute(post);
		try {
			if(logger.isInfoEnabled()){
				logger.info(url+"\t httpClient post response:"+resp.getStatusLine());
			}
		    HttpEntity entity = resp.getEntity();
		    return EntityUtils.toString(entity, Consts.UTF_8);
		} finally {
			client.getConnectionManager().shutdown();
		}
	}

	private HttpClient getHttpClient(){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}


	private List<NameValuePair> createParams(Map<String,String> params){
		if(params == null) return null;
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

}
