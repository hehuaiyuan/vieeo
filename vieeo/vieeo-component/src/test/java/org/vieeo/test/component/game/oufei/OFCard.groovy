package org.vieeo.test.component.game.oufei

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("card")
class OFCard {

	@XStreamAlias("cardid")
	private String cardid;

	@XStreamAlias("classid")
	private String classid;

	@XStreamAlias("cardname")
	private String cardname;

	@XStreamAlias("othername")
	private String othername;

	@XStreamAlias("detail")
	private String detail;

	@XStreamAlias("compty")
	private String compty;

	@XStreamAlias("usecity")
	private String usecity;

	@XStreamAlias("usemethod")
	private String usemethod;

	@XStreamAlias("fullcostsite")
	private String fullcostsite;

	@XStreamAlias("proare")
	private String proare;

	@XStreamAlias("serviceNum")
	private String serviceNum;

}
