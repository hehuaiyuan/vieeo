package org.vieeo.test.component.other
import static org.junit.Assert.*

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec

import org.apache.commons.codec.binary.Base64
import org.junit.Test

import com.vieeo.component.http.HttpClientComponent


class TestHttpClientComponent {

	@Test
	public void test() {
		try {
			HttpClientComponent client = new HttpClientComponent();

			//String url = "http://api.thingture.com/Api/QueryAllProduct";

			//String url = "http://api.thingture.com/Api/QueryProductDetails";

			String url = "http://esales.sdptest.sdo.com/webservice/DepositService.ashx";

			def paramStr = getParams();
			Map<String,String> params = [depositParams:paramStr ];

			System.out.println(client.doPost(url, params));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private String getParams(){
		def pwd = "129008";
		def reqStringToEncrypt = "huangdxx01|${pwd}|千军破盛大版|200683100|1|abc123|23|0|0|-9999|1|10|10.132.97.43|Shop|L232016856|||";

		byte[] chargeData4Send = reqStringToEncrypt.getBytes("UTF-8");
		chargeData4Send = CBCEncrypt( chargeData4Send );
		String chargeData4SendAfterDes3 = Base64.encodeBase64String( chargeData4Send );
		return chargeData4SendAfterDes3;
	}

	public byte[] CBCEncrypt(byte[] data) {
		byte[] key = Base64.decodeBase64("e6XAMUwQ22s=");
		byte[] iv = Base64.decodeBase64("TQvuakTrVhg=");
		try {
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// 若采用NoPadding模式，data长度必须是8的倍数
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

			// 用密匙初始化Cipher对象
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);

			// 执行加密操作
			byte[] encryptedData = cipher.doFinal(data);

			return encryptedData;
		} catch (Exception e) {
			System.err.println("DES算法，加密数据出错!");
			e.printStackTrace();
		}

		return null;
	}

}
