package com.vieeo.component.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转换boolean 类型converter
 * @author roy.he
 *
 */
public class StringToBooleanConverter implements Converter<String,Boolean>{
	
	private static final String BOOLEAN_TRUE="true";
	private static final String BOOLEAN_FALSE="false";
	
	//字符串表示true的值
	private String trueValue;
	
	//字符串表示false的值
	private String falseValue;
	
	@Override
	public Boolean convert(String value) {
		if(StringUtils.isBlank(value)) return false;
		value = StringUtils.trim(value);
		if(value.equals(trueValue)) return true;
		if(value.equals(falseValue)) return false;
		if(BOOLEAN_TRUE.equals(value)) return true;
		if(BOOLEAN_FALSE.equals(value)) return false;
		return false;
	}

	public String getTrueValue() {
		return trueValue;
	}

	public void setTrueValue(String trueValue) {
		this.trueValue = trueValue;
	}

	public String getFalseValue() {
		return falseValue;
	}

	public void setFalseValue(String falseValue) {
		this.falseValue = falseValue;
	}

}
