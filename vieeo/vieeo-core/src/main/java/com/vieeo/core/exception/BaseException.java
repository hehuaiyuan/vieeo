package com.vieeo.core.exception;

public class BaseException extends Exception {

    private static final long serialVersionUID = 2506910560789987267L;

    public BaseException() {
        super();
    }

    /**
     * @param message
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}

