package com.vieeo.orm.jdbc;

import com.vieeo.core.exception.BaseException;

public class JdbcException extends BaseException{

	private static final long serialVersionUID = 7522700391744163790L;

	public JdbcException(){
		super();
	}

	/**
	 * @param message
	 */
	public JdbcException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public JdbcException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JdbcException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
