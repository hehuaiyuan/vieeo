package org.vieeo.test.component.game.gamemapping.model

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("paymethod")
class PaymethodMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("area")
	private String area;
}
