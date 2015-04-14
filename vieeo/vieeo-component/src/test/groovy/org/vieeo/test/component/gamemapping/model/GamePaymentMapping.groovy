package org.vieeo.test.component.gamemapping.model

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute
import com.thoughtworks.xstream.annotations.XStreamConverter
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter

@XStreamAlias("paymethod")
@XStreamConverter(value=ToAttributedValueConverter.class, strings=["content"])
class GamePaymentMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	private String content;

}
