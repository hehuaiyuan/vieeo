package org.vieeo.test.component.gamemapping.model;

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("root")
class GameListMapping {

	@XStreamAlias("items")
	private List<ItemMapping> items;

	@XStreamAlias("paymethods")
	private List<GamePaymentMapping> paymethods;

}
