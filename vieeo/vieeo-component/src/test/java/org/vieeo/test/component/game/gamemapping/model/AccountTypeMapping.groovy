package org.vieeo.test.component.game.gamemapping.model

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute
import com.thoughtworks.xstream.annotations.XStreamConverter
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter

@XStreamAlias("accounttype")
@XStreamConverter(value=ToAttributedValueConverter.class, strings=["content"])
class AccountTypeMapping {

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	private String content;

}
