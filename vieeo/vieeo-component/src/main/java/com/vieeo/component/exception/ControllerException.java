package com.vieeo.component.exception;

//针对spring controller定义的异常
public class ControllerException extends RuntimeException{

	private static final long serialVersionUID = 498912921073395858L;
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(RuntimeException cause,String message) {
		super(message,cause);
	}

}
