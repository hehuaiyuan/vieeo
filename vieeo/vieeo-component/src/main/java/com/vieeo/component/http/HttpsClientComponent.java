package com.vieeo.component.http;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpsClientComponent extends BaseHttpClientComponent{

    private int port = 443;

    private boolean allowAllCert = true;

    private String trustStorePath;

    private String password = "nopassword";

	private static Logger logger = Logger.getLogger(HttpsClientComponent.class);

	public String doGet(String url,Map<String,String> params) throws Exception{
        HttpClient client = getHttpClient();
        initSSLSocketFactory(client);
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
		initSSLSocketFactory(client);
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

	private void initSSLSocketFactory(HttpClient client){
	    try {
	        SSLSocketFactory socketFactory = null;
	        if(allowAllCert){
	            SSLContext context = createSSLContext();
	            socketFactory = new SSLSocketFactory(context,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        }else {
	            KeyStore store = getKeyStore();
	            socketFactory = new SSLSocketFactory(store);
	        }
	        Scheme sch = new Scheme("https", port, socketFactory);
	        client.getConnectionManager().getSchemeRegistry().register(sch);
	    }catch(Exception e){
	        e.printStackTrace();
	        throw new RuntimeException("init SSLFactory error:"+e.getMessage(),e);
	    }
	}

	private SSLContext createSSLContext(){
	    SSLContext sslcontext = null;
	      try {
	          sslcontext = SSLContext.getInstance(SSLSocketFactory.SSL);
	          sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()},
	                  new java.security.SecureRandom());
	      } catch (Exception e) {
	          e.printStackTrace();
	          throw new RuntimeException("init SSLContext error:"+e.getMessage(),e);
	      }
	      return sslcontext;
	}

	private KeyStore getKeyStore(){
	    KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream instream = new FileInputStream(new File(trustStorePath));
            try {
                trustStore.load(instream, password.toCharArray());
            } finally {
                try { instream.close(); } catch (Exception ignore) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("init KeyStore error:"+e.getMessage(),e);
        }
        return trustStore;
	}

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isAllowAllCert() {
        return allowAllCert;
    }

    public void setAllowAllCert(boolean allowAllCert) {
        this.allowAllCert = allowAllCert;
    }

    public String getTrustStorePath() {
        return trustStorePath;
    }

    public void setTrustStorePath(String trustStorePath) {
        this.trustStorePath = trustStorePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
