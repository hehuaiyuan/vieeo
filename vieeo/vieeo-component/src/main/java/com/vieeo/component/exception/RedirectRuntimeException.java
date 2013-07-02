package com.vieeo.component.exception;

/**
 * 重定向页面异常，当出错需要返回一个重定向的页面时，抛出此异常
 * @author roy.he
 *
 */
public class RedirectRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 4097969192309364161L;
	
	//重定向url
	private String redirectUrl;

	public RedirectRuntimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public RedirectRuntimeException(String message,String redirectUrl) {
		super(message);
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @param cause
	 */
	public RedirectRuntimeException(RuntimeException cause,String redirectUrl) {
		super(cause);
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RedirectRuntimeException(String message, RuntimeException cause,String redirectUrl) {
		super(message, cause);
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
