package org.vieeo.test.component.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.vieeo.component.http.HttpClientComponent;
import com.vieeo.util.crypto.MD5;

public class TestHttpClientComponent extends TestCase{

	public void testPost() throws Exception{
		try {
			HttpClientComponent client = new HttpClientComponent();

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			String url = "http://10.132.97.60:8083/rest/executeAll";

			//String url = "https://cardpaytest.shengpay.com/web-acquire-channel/query/batch/orders.htm";


			Map<String,String> data = new HashMap<String,String>();
			data.put("merchantNo", "703424");
			data.put("payChannel","75");
			data.put("cardNo", "123456");
			data.put("cardType", "CM");
			data.put("parValue", "10");
			data.put("customerIp", "");

			RestTemplate rt = new RestTemplate();
			// 请求参数
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("policyNoList", "P0011");
		    params.put("kvData", "{\"cardNo\":\"321321321\",\"cardType\":\"JWK\",\"customerIp\":\"127.0.0.1\",\"errorCode\":\"F0401\",\"merchantNo\":\"245888\"}");

		    System.out.println(client.doPost(url, params));


			/*String transNo = "C20141210175544315643";
			String merchantOrderNo = "";
			String beginTime = "";
			String endTime = "";
			String startRow = "0";
			String endRow = "5";
			String merchantNo = "703424";
			String transStatus = "";
			String signType = "MD5";
			String paySuccessBeginTime = "2014-12-10 00:00:00";
			String paySuccessEndTime = "2014-12-11 00:00:00";

			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paySuccessBeginTime);
			Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paySuccessEndTime);

			long time2 = end.getTime();
			long time1 = start.getTime();
			long between_days = (time2 - time1) / (1000 * 60);
			System.out.println(between_days);

			StringBuilder source = new StringBuilder();
			source.append(merchantNo);
			source.append(signType);
			source.append(beginTime);
			source.append(endTime);
			source.append(startRow);
			source.append(endRow);
			source.append(paySuccessBeginTime);
			source.append(paySuccessEndTime);
			source.append(transStatus);
			source.append("abcdefg");

			String mac = MD5.getInstance().calcMD5(source.toString());
			System.out.println(source);
			System.out.println(mac);

			Map<String,String> params = new HashMap<String,String>();
			params.put("beginTime", beginTime);
			params.put("endTime",endTime);
			params.put("startRow", startRow);
			params.put("endRow", endRow);
			params.put("merchantNo", merchantNo);
			params.put("transNo", transNo);
			params.put("merchantOrderNo", merchantOrderNo);
			params.put("signType", signType);
			params.put("paySuccessBeginTime", paySuccessBeginTime);
			params.put("paySuccessEndTime", paySuccessEndTime);
			params.put("transStatus", transStatus);
			params.put("mac", StringUtils.upperCase(mac));

			System.out.println(client.doPost(url, params));*/
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
