package org.vieeo.test.component.qingtian;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.component.http.HttpClientComponentUtils
import com.vieeo.util.crypto.MD5

class TestQianTianHttp {


	public void test() {

		HttpClientComponent client = new HttpClientComponent();
		def url="http://api.zgbbc.com/api/ApiQuery.aspx";

		String md5key = "08732442845b6c2fc4054ed677a2316e";

		Map<String,String> params = ["hzhbUserId":"10035"];
		String signStr = createGetParamsWithSort(params)+md5key;

		String encodeParams = HttpClientComponentUtils.createEncodeGetParamsWithSort(params,"GBK");
		encodeParams = encodeParams+ "&sign="+MD5.getInstance().calcMD5(signStr);
		println encodeParams;
		String gameXml =  client.doGet(url, encodeParams);

		String PATH = "D:/新建文件夹/qingtian/";
		BufferedWriter writer = new File(PATH+"game.xml").newWriter('gbk');
		gameXml.eachLine{line -> writer.writeLine(line); }
		writer.flush();
		writer.close();
	}

	@Test
	public void testDetail(){
		HttpClientComponent client = new HttpClientComponent();
		def url= "http://api.zgbbc.com/admin/business/goodsinfo.aspx";

		String md5key = "08732442845b6c2fc4054ed677a2316e";

		Map<String,String> params = ["hzhbUserId":"10035","goodsId":"10243"];
		String signStr = createGetParamsWithSort(params)+md5key;

		String encodeParams = HttpClientComponentUtils.createEncodeGetParamsWithSort(params,"GBK");
		encodeParams = encodeParams+ "&sign="+MD5.getInstance().calcMD5(signStr);
		println encodeParams;
		String gameXml =  client.doGet(url, encodeParams);
		println gameXml;

		String PATH = "D:/新建文件夹/qingtian/";
		BufferedWriter writer = new File(PATH+"gameItem.xml").newWriter('UTF-8');
		gameXml.eachLine{line -> writer.writeLine(line); }
		writer.flush();
		writer.close();
	}

	public static String createGetParamsWithSort(Map<String,String> params) throws UnsupportedEncodingException{
		if(params == null || params.size() <= 0) return null;
		StringBuilder result = new StringBuilder();
		Set<String> keys = params.keySet();
		List<String> listKey = new ArrayList<String>(keys);
		Collections.sort(listKey);
		for (int i=0;i<listKey.size();i++) {
			String key = listKey.get(i);
			String value = params.get(key);
			result.append(key).append(value);
		}
		return result.toString();
	}

}
