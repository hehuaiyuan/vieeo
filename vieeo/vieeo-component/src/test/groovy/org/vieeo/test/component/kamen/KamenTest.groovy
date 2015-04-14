package org.vieeo.test.component.kamen;

import static org.junit.Assert.*

import java.text.SimpleDateFormat

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.component.http.HttpClientComponentUtils
import com.vieeo.util.crypto.MD5

class KamenTest {

	HttpClientComponent httpComponent = new HttpClientComponent();

	@Test
	public void test() {
		def url = "http://ccapi.kamenwang.com/interface/method";
		def params = ["method":"kamenwang.catalogs.get",
			"timestamp": new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
			"format":"json","customerid":"801496","v":"1.0"];


		String md5key = "E5BADC48F0E6904E15FBE0F1C2D41930";
		String urlParams = HttpClientComponentUtils.createGetParamsWithSort(params);
		urlParams = urlParams+md5key;
		println urlParams;
		String encodeParams = HttpClientComponentUtils.createEncodeGetParamsWithSort(params);
		encodeParams = encodeParams+ "&sign="+MD5.getInstance().calcMD5(urlParams);
		println encodeParams;
		encodeParams = encodeParams.replace("+", "%20");
		println encodeParams;
		println httpComponent.doGet(url, encodeParams);
	}

}
