package org.vieeo.test.component.game.gamemapping.model;

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute

class DeposititemMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("gameid")
	@XStreamAsAttribute
	private String gameid;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamAlias("uuid")
	@XStreamAsAttribute
	private String uuid;

	@XStreamAlias("playid")
	@XStreamAsAttribute
	private String playid;

	@XStreamAlias("areas")
	private List<AreaMapping> areas;

	@XStreamAlias("paymethods")
	private List<PaymethodMapping> paymethods;

	@XStreamAlias("consumes")
	private List<ConsumeMapping> consumes;


}
