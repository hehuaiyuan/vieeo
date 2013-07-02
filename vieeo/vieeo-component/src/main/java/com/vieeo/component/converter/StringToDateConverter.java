package com.vieeo.component.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.vieeo.util.date.DateUtils;

/**
 * String 转换date 类型converter
 * @author roy.he
 *
 */
public class StringToDateConverter implements Converter<String,Date>{
	
	//转换模式,默认yyyy-MM-dd hh:mm:ss
	private String pattern = DateUtils.PATTERN_YYYYMMDD_HHMMSS;

	@Override
	public Date convert(String value) {
		if(StringUtils.isBlank(value)) return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
