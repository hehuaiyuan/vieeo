package com.vieeo.module.repository;

public class RepositoryException extends RuntimeException{

	private static final long serialVersionUID = 7063439496108848207L;

	public RepositoryException(String message,Exception e){
		super(message,e);
	}
	
	public RepositoryException(String message){
		super(message);
	}

}
