package org.vieeo.test.component.game.gamemapping.model;

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("item")
class ItemMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamAlias("uuid")
	@XStreamAsAttribute
	private String uuid;

	@XStreamAlias("playid")
	@XStreamAsAttribute
	private String playid;

	@XStreamAlias("unifiedarea")
	@XStreamAsAttribute
	private String unifiedarea;

	@XStreamAlias("showserver")
	@XStreamAsAttribute
	private String showserver;

	@XStreamAlias("region")
	@XStreamAsAttribute
	private String region;

	@XStreamAlias("gameid")
	@XStreamAsAttribute
	private String gameid;

	@XStreamAlias("spell")
	@XStreamAsAttribute
	private String spell;

}
