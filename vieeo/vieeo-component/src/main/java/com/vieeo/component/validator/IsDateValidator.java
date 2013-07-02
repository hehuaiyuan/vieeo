package com.vieeo.component.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vieeo.component.validator.annotations.IsDate;

/**
 * 日期验证Validator
 * @author roy.he
 *
 */
public class IsDateValidator implements ConstraintValidator<IsDate, String> {
	
	private String pattern;

	@Override
	public void initialize(IsDate constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		 try {  
	            SimpleDateFormat sf = new SimpleDateFormat(pattern);  
	            sf.setLenient(false);  
	            sf.parse((String)value);  
	            return true;  
	        } catch (ParseException pe) {  
	            return false;  
	        }  
	}

}
