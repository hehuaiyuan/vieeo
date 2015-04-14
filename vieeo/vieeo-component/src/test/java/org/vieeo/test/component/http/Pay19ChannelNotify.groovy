package org.vieeo.test.component.http;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.util.crypto.MD5

class Pay19ChannelNotify {

	@Test
	public void test() {
		try {
			HttpClientComponent client = new HttpClientComponent();

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			String url = "http://cardchannel.shengpay.com/paychannel-service-web/api/ofpay/notify.html";

			BufferedReader reader = new File("D:\\data.txt").newReader('utf-8');

			reader.eachLine {line ->
				def data = line.split(",");

				String usercode = "A927924";
				String version = "1.0";
				String result = "2000";
				String mode = "q";
				String datetime = "20140915085413";
				String info = "channel success mock notify ";
				String orderno = data[0];
				String accountvalue = data[1];
				String value = data[1];
				String billid = "R${new Date().format('yyyyMMddhhmmssss')}";
				String md5key = "hDd0eG6h4bYMiRFdip4oSgeC80aBKEFz";
				String sign = "${usercode}${mode}${version}${orderno}${billid}${result}${info}${value}${accountvalue}${datetime}${md5key}";

				Map<String,String> params = [:];
				params.put("usercode", usercode);
				params.put("version", version);
				params.put("result", result);
				params.put("mode", mode);
				params.put("datetime",datetime);
				params.put("info", info);
				params.put("orderno", orderno);
				params.put("accountvalue", accountvalue);
				params.put("value", value);
				params.put("sign", MD5.getInstance().calcMD5(sign));
				params.put("billid", billid);

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
