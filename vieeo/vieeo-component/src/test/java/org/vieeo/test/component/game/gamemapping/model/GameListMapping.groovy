package org.vieeo.test.component.game.gamemapping.model;

import com.thoughtworks.xstream.annotations.XStreamAlias

@XStreamAlias("root")
class GameListMapping {

	@XStreamAlias("items")
	private List<ItemMapping> items;

	@XStreamAlias("paymethods")
	private List<GamePaymentMapping> paymethods;

	@XStreamAlias("accounttypes")
	private List<AccountTypeMapping> accounttypes;

}
