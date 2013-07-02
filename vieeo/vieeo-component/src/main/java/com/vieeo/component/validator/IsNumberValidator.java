package com.vieeo.component.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import com.vieeo.component.validator.annotations.IsNumber;

/**
 * 判断值是否为数字,包含小数点
 * @author roy.he
 *
 */
public class IsNumberValidator implements ConstraintValidator<IsNumber, String>{
	
	//private static String reg = "\\d*|\\d+.\\d+";
	
	//private static Pattern pattern = Pattern.compile(reg);
	
	@Override
	public void initialize(IsNumber constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isBlank(value)) return true;
		String reg = "\\d*|\\d+.\\d+";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(value).matches();
	}
}
