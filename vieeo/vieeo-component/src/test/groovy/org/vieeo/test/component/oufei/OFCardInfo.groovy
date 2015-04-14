package org.vieeo.test.component.oufei

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute

@XStreamAlias("cardinfo")
class OFCardInfo {

	@XStreamAlias("err_msg")
	private String err_msg;

	@XStreamAlias("retcode")
	private String retcode;

	@XStreamAlias("ret_cardinfos")
	private List<OFCard> cards;

}
