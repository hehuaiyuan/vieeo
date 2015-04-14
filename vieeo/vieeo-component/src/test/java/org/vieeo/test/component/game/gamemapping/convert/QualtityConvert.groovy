package org.vieeo.test.component.game.gamemapping.convert

import org.vieeo.test.component.game.gamemapping.model.QuantityMapping

import com.thoughtworks.xstream.converters.Converter
import com.thoughtworks.xstream.converters.MarshallingContext
import com.thoughtworks.xstream.converters.UnmarshallingContext
import com.thoughtworks.xstream.io.HierarchicalStreamReader
import com.thoughtworks.xstream.io.HierarchicalStreamWriter

class QualtityConvert implements Converter {

	@Override
	public boolean canConvert(Class class1) {
		return true;
	}

	@Override
	public void marshal(Object obj,
			HierarchicalStreamWriter writer,
			MarshallingContext context) {
		QuantityMapping quantity = (QuantityMapping)obj;
		writer.addAttribute("name", quantity.name);
		writer.value = quantity.value;
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		String name = reader.getAttribute("name");
		QuantityMapping quantity = new QuantityMapping(
			name:name,
			value:reader.value
		);
		return quantity;
	}

}
