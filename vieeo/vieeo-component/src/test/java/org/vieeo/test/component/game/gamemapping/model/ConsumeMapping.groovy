package org.vieeo.test.component.game.gamemapping.model

import org.vieeo.test.component.game.gamemapping.convert.QualtityConvert

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute
import com.thoughtworks.xstream.annotations.XStreamConverter

@XStreamAlias("consume")
class ConsumeMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamAlias("linearity")
	@XStreamAsAttribute
	private String linearity;

	@XStreamAlias("minparvalue")
	@XStreamAsAttribute
	private String minparvalue;

	@XStreamAlias("maxparvalue")
	@XStreamAsAttribute
	private String maxparvalue;

	@XStreamAlias("area")
	private String area;

	@XStreamAlias("price")
	private String price;

	@XStreamAlias("quantity")
	@XStreamConverter(QualtityConvert.class)
	private QuantityMapping quantity;
}
