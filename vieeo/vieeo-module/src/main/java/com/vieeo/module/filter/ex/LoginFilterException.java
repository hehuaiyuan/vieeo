package com.vieeo.module.filter.ex;

import javax.servlet.ServletException;

public class LoginFilterException extends ServletException{

	private static final long serialVersionUID = 2927836460638539061L;
	
	public LoginFilterException(String msg){
		super(msg);
	}

}
