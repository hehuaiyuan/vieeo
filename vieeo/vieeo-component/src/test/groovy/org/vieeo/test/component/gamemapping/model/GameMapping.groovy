package org.vieeo.test.component.gamemapping.model;

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("root")
class GameMapping {

	@XStreamAlias("deposititem")
	private DeposititemMapping deposititem;

}
