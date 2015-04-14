package org.vieeo.test.component.http;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.util.crypto.MD5

class XqtChannelNotify {

	@Test
	public void test() {
		try {
			HttpClientComponent client = new HttpClientComponent();

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			String url = "http://cardchannel.shengpay.com/paychannel-service-web/api/onsean/notify.htm";

			BufferedReader reader = new File("D:\\clorders2.csv").newReader('utf-8');

			reader.eachLine {line ->
				def data = line.split(",");

				String customerid = "153052";
				String errcode = "1010";
				String mark = data[1];
				String state = "0";
				String message = "channel failed mock notify ";
				String sdcustomno = data[0];
				String usermoney = "0";
				String sd51no = "S${new Date().format('yyyyMMddhhmmssss')}";
				String md5key = "316048b7d8dbabe6c001051adf6f46c7";
				String sign = "customerid=${customerid}&sd51no=${sd51no}&sdcustomno=${sdcustomno}&mark=${mark}&key=${md5key}";

				Map<String,String> params = [:];
				params.put("customerid", customerid);
				params.put("mark", mark);
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
