package org.vieeo.test.component.zhx.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PAY")
public class ZhxReceiveRequest {

	@XStreamAlias("BODY")
	private ZhxReceiveRequestBody body;

	public ZhxReceiveRequestBody getBody() {
		return body;
	}

	public void setBody(ZhxReceiveRequestBody body) {
		this.body = body;
	}


}
