package com.vieeo.component.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * 为了封装新异常消息，扩展BindException
 * @author roy.he
 *
 */
public class MsgBindException extends BindException{

	private static final long serialVersionUID = 9210579463473432805L;
	
	private String message ;

	public MsgBindException(BindingResult bindingResult) {
		super(bindingResult);
	}
	
	public MsgBindException(String message,BindingResult bindingResult) {
		this(bindingResult);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return StringUtils.isBlank(message) ? super.getMessage() : message;
	}
	
	@Override
	public boolean equals(Object other) {
		return (this == other || getBindingResult().equals(other));
	}
	
	@Override
	public int hashCode() {
		return getBindingResult().hashCode();
	}

}
