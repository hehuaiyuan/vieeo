package org.vieeo.test.component.http;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.util.crypto.MD5

class OFChannelNotify {

	@Test
	public void test() {
		try {
			HttpClientComponent client = new HttpClientComponent();

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			String url = "http://cardchannel.shengpay.com/paychannel-service-web/api/card70/notify.html";

			BufferedReader reader = new File("D:\\clorders2.csv").newReader('utf-8');

			reader.eachLine {line ->
				def data = line.split(",");

				String userid = "5452";
				String errtype = "0";
				String ext = "";
				String returncode = "0";
				String message = "channel failed mock notify ";
				String money = data[1];
				String orderno = data[0];
				String realmoney = "0";
				String usermoney = "0";
				String yzchorderno = "S${new Date().format('yyyyMMddhhmmssss')}";
				String md5key = "c7a9c1b0bc678954f4b4b565e20ca613";
				String sign = "returncode=${returncode}&yzchorderno=${yzchorderno}&userid=${userid}&orderno=${orderno}&money=${money}&realmoney=${realmoney}&keyvalue=${md5key}";

				Map<String,String> params = [:];
				params.put("userid", userid);
				params.put("errtype", errtype);
				params.put("returncode", returncode);
				params.put("ext", ext);
				params.put("message",message);
				params.put("money", money);
				params.put("orderno", orderno);
				params.put("realmoney", realmoney);
				params.put("usermoney", usermoney);
				params.put("sign", MD5.getInstance().calcMD5(sign));
				params.put("yzchorderno", yzchorderno);

				println "${orderno} notify result:"+client.doPost(url, params);
				Thread.sleep(500);
			};
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
			println e;
		}
	}

}
