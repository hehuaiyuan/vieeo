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

import com.vieeo.component.validator.IsDateValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })  
@Retention(RUNTIME)  
@Constraint(validatedBy = IsDateValidator.class)  
@Documented  
public @interface IsDate {  //日期验证
	String message() default "日期格式不正确";  
    Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
    String pattern();  
}
