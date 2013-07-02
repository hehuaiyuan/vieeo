/**
 * project : DH-Monitor-server
 * user created : pippo
 * date created : 2009-2-12 - 下午06:09:57
 */
package com.vieeo.orm.jdbc.support;

import com.vieeo.orm.jdbc.JdbcException;


/**
 * @since 2009-2-12
 * @author pippo
 */
public class CollectorException extends JdbcException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4988454504814172550L;

	/**
	 *
	 */
	public CollectorException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CollectorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CollectorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CollectorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
