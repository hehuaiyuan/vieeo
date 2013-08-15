package com.vieeo.component.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

@SuppressWarnings("rawtypes")
public class XStreamComponent {

	private XStream xstream;

	private Class<?>[] classes;

	//在序列化XML到类时是否对XML存在类中不存在字段做检查
	private boolean fieldCheck = false;

	public void init(){
		if(fieldCheck) xstream = new XStream();
		else {
			xstream = new XStream() {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return new MapperWrapper(next) {
						@Override
						public boolean shouldSerializeMember(Class definedIn,String fieldName) {
							if (definedIn == Object.class) {
								return false;
							}
							return super
									.shouldSerializeMember(definedIn, fieldName);
						}
					};
				}
			};
		}
		xstream.processAnnotations(classes);
	}

	@SuppressWarnings("unchecked")
	public <T> T parseXML(String xml) {
		if(xstream == null) init();
		return (T) xstream.fromXML(xml);
	}

	public String toXML(Object obj) {
		if(xstream == null) init();
		return xstream.toXML(obj);
	}

	public Class<?>[] getClasses() {
		return classes;
	}

	public void setClasses(Class<?>[] classes) {
		this.classes = classes;
	}

	public boolean isFieldCheck() {
		return fieldCheck;
	}

	public void setFieldCheck(boolean fieldCheck) {
		this.fieldCheck = fieldCheck;
	}

	public XStream getXstream() {
		return xstream;
	}

	public void setXstream(XStream xstream) {
		this.xstream = xstream;
	}

}
