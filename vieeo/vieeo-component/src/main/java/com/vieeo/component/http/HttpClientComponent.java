package com.vieeo.component.http;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientComponent extends BaseHttpClientComponent{

	private static Logger logger = Logger.getLogger(HttpClientComponent.class);

	public String doGet(String url,Map<String,String> params) throws Exception{
        HttpClient client = getHttpClient();
        String urlParams = createGetParams(params);
        HttpGet get = new HttpGet(url + urlParams);
        //如果服务端接收的提交数据是json格式,要设置header
        //post.addHeader(new BasicHeader("Content-Type","applicatin/json"));
        //ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpResponse resp = client.execute(get);
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

	public String doPost(String url,Map<String,String> params) throws Exception{
		HttpClient client = getHttpClient();

		HttpPost post = new HttpPost(url);
		//如果服务端接收的提交数据是json格式,要设置header
		//post.addHeader(new BasicHeader("Content-Type","applicatin/json"));

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

}
