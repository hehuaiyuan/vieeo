package org.vieeo.test.component.zhx.model;

import static org.junit.Assert.*

import org.apache.http.HttpEntity
import org.apache.http.entity.StringEntity
import org.junit.Test

import com.vieeo.component.http.HttpClientComponent
import com.vieeo.component.xstream.XStreamComponent

class TestZHXHttpClientComponent {

	private XStreamComponent zhxComponent;

	@Test
	public void test() {
		try {
			String url = "http://www.zihexin.cn:8003/paytest/order/doParseUrlGenOrderAsXML.htm";

			HttpClientComponent client = new HttpClientComponent();

			def zoneClasses = [
			       			ZhxReceiveRequest.class,
			       			ZhxReceiveRequestBody.class
			       		];

			zhxComponent = new XStreamComponent(classes:zoneClasses,fieldCheck:false);

			ZhxReceiveRequest request = new ZhxReceiveRequest();

			ZhxReceiveRequestBody body = new ZhxReceiveRequestBody();

			String xml = zhxComponent.toXML(request);

			HttpEntity entity = new StringEntity(xml);

			System.out.println(client.doPost(url, entity));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
