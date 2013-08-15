package org.vieeo.test.component.http;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.vieeo.component.http.HttpClientComponent;

public class TestHttpClientComponent extends TestCase{

	public void testPost() throws Exception{
		try {
			HttpClientComponent client = new HttpClientComponent();

			String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			Map<String,String> params = new HashMap<String,String>();
			params.put("ProductID", "10013");

			System.out.println(client.doPost(url, params));
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
