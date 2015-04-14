package com.vieeo.component.xstream;

import java.util.ArrayList;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XStreamOmitClassConvert implements Converter{

	@Override
	public boolean canConvert(Class type) {
		return ArrayList.class.equals(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		/*List<NamedParameter> params = (List<NamedParameter>)source;
		
		for (NamedParameter param : params) {
			writer.startNode("key");
			writer.setValue(param.getKey());
			writer.endNode();
			writer.startNode("value");
			writer.setValue(param.getValue());
			writer.endNode();
		}*/
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
