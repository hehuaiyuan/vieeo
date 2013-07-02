package com.vieeo.core.exception;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -2944409554553356920L;

	public BaseRuntimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public BaseRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BaseRuntimeException(RuntimeException cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseRuntimeException(String message, RuntimeException cause) {
		super(message, cause);
	}

}
