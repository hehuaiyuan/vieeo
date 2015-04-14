package com.vieeo.component.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
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

public class HttpsClientComponent extends HttpClientComponentUtils{

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
        //get.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
        //get.addHeader(new BasicHeader("Accept","*/*"));
        //get.addHeader(new BasicHeader("Referer","https://kyfw.12306.cn/otn/leftTicket/init"));
        //get.addHeader(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.72 Safari/537.36"));
        //get.addHeader(new BasicHeader("Host","kyfw.12306.cn"));
        //get.addHeader(new BasicHeader("Cookie","JSESSIONID=9E27B965BC15AE821288DA42EB6CADFC; BIGipServerotn=2278818058.38945.0000; _jc_save_fromStation=%u82CF%u5DDE%2CSZH; _jc_save_toStation=%u682A%u6D32%2CZZQ; _jc_save_fromDate=2014-01-27; _jc_save_toDate=2014-02-24; _jc_save_wfdc_flag=dc"));
        //get.addHeader(new BasicHeader("X-Requested-With","XMLHttpRequest"));
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
	            SSLContext context = createSSLContext(); //createTSLContext();
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

	private SSLContext createTSLContext(){
	    SSLContext sslcontext = null;
	      try {
	          sslcontext = SSLContext.getInstance(SSLSocketFactory.TLS);
	          sslcontext.init(null, getManagers(), new SecureRandom());
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

	/**
	 * 读取公钥
	 *
	 * @return
	 * @throws FileNotFoundException
	 * @throws CertificateException
	 */
	private X509Certificate[] getCerts() throws FileNotFoundException,
			CertificateException {
		if(StringUtils.isBlank(trustStorePath)) return null;
		File file = new File(trustStorePath);
		if(!file.exists()) throw new FileNotFoundException("can not find cert file");
		FileInputStream fis = new FileInputStream(file);
		CertificateFactory cf = CertificateFactory.getInstance("X509");
		X509Certificate c = (X509Certificate) cf.generateCertificate(fis);
		return new X509Certificate[] { c };
	}

	// 解析证书上下文环境
	private TrustManager[] getManagers() throws FileNotFoundException,
			CertificateException {
		final X509Certificate[] certs = getCerts();
		if(certs == null){
			return new TrustManager[] { new javax.net.ssl.X509TrustManager() {
				@Override
			    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			    }

			    @Override
			    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			    }

			    @Override
			    public X509Certificate[] getAcceptedIssuers() {
			        return new X509Certificate[]{};
			    }
			} };
		}else {
			return new TrustManager[] { new javax.net.ssl.X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return certs;
				}

				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs, String authType) {
				}
			} };
		}
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
