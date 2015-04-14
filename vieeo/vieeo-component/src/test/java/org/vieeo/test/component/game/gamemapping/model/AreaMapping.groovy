package org.vieeo.test.component.game.gamemapping.model

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute

@XStreamAlias("area")
class AreaMapping {

	@XStreamAlias("groups")
	private List<GroupMapping> groups;

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamAlias("playid")
	@XStreamAsAttribute
	private String playid;

	@XStreamAlias("uuid")
	@XStreamAsAttribute
	private String uuid;
}
