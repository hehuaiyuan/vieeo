package org.vieeo.test.component.oufei;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.component.xstream.XStreamComponent
import com.vieeo.util.crypto.MD5

class TestOuFei {

	@Test
	public void test() {
		HttpClientComponent client = new HttpClientComponent();
		//def url="http://api2.ofpay.com/querybigcard.do";
		//def url="http://api2.ofpay.com/querylist.do";

		def url="http://api2.ofpay.com/querycardinfo.do";

		String md5key = "08732442845b6c2fc4054ed677a2316e";
		String pwd = "sfutongka@11^&com";


		Map<String,String> params = ["userid":"A919201","userpws":MD5.getInstance().calcMD5(pwd).toLowerCase(),"cardid":"2206","version":"6.0"];

		String gameXml =  client.doGet(url, params);

		//String s = "%3CP%3E%C7%EB%CC%E1%B9%A9%C4%FA%B5%C4%D5%CB%BA%C5%D0%C5%CF%A2%A3%AC%C5%B7%B7%C9%BD%AB%B0%EF%C4%FA%CD%EA%B3%C9%B3%E4%D6%B5%A3%A1%3C%2FP%3E";

		//println URLDecoder.decode(s,"gbk");

		String PATH = "D:/新建文件夹/oufei/";
		BufferedWriter writer = new File(PATH+"Q_coin.xml").newWriter('gbk');
		gameXml.eachLine{line -> writer.writeLine(line); }
		writer.flush();
		writer.close();

	}


	public void testConvert(){
		def classes = [
			OFCardInfo.class,
			OFCard.class
		];
		XStreamComponent gameComponent = new XStreamComponent(classes:classes,fieldCheck:false);

		BufferedReader reader = new File("D:\\新建文件夹\\oufei\\games.xml").newReader("GB2312");
		String xml = reader.getText();
		reader.close();
		OFCardInfo info = gameComponent.parseXML(xml);

		info.cards.each { card ->
			card.detail = URLDecoder.decode(card.detail,"gbk");
			card.compty = URLDecoder.decode(card.compty,"gbk");
			card.usemethod = URLDecoder.decode(card.usemethod,"gbk");
			card.serviceNum = URLDecoder.decode(card.serviceNum,"gbk");
		};

		BufferedWriter writer = new File("D:/新建文件夹/oufei/"+"games_chs.xml").newWriter('gbk');
		writer.write(gameComponent.toXML(info));
		writer.flush();
		writer.close();


	}

}
