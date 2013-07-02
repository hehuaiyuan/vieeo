package com.vieeo.component.exception;

/**
 * json请求错误异常,当返回格式为json字符串时，出错抛出此异常标示
 * @author roy.he
 *
 */
public class JsonRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 4097969192309364161L;
	
	public JsonRuntimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public JsonRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JsonRuntimeException(RuntimeException cause,String msg) {
		super(msg,cause);
	}

}
