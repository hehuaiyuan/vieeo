package org.vieeo.test.component.http;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.vieeo.component.http.HttpsClientComponent;

public class TestHttpsClientComponent extends TestCase{

	public void testPost() throws Exception{
		try {
		    HttpsClientComponent client = new HttpsClientComponent();
		    client.setAllowAllCert(true);
		    client.setTrustStorePath("E:\\railway\\srca12306\\srca.cer");
		    client.setPassword("123456");

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			//String url = "https://www.google.com/";

			String url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2014-01-27&leftTicketDTO.from_station=SZH&leftTicketDTO.to_station=ZZQ&purpose_codes=ADULT";
		    //String  url  = "https://kyfw.12306.cn/otn/login/userLogin";

			Map<String,String> params = new HashMap<String,String>();
			params.put("leftTicketDTO.train_date", "2014-01-27");
			params.put("leftTicketDTO.from_station", "SZH");
			params.put("leftTicketDTO.to_station", "ZZQ");
			params.put("purpose_codes", "ADULT");

			System.out.println(client.doGet(url, params));
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
