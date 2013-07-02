package com.vieeo.component.validator.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vieeo.component.validator.IsNumberValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })  
@Retention(RUNTIME)  
@Constraint(validatedBy = IsNumberValidator.class) 
@Documented 
public @interface IsNumber {
	String message() default "格式必须为数字";  
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};  
}
