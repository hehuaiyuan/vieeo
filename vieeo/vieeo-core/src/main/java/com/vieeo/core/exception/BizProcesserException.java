package com.vieeo.core.exception;

public class BizProcesserException extends BaseException{

	private static final long serialVersionUID = -7956083486342380469L;

	public BizProcesserException() {
        super();
    }

    /**
     * @param message
     */
    public BizProcesserException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BizProcesserException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BizProcesserException(String message, Throwable cause) {
        super(message, cause);
    }
}
