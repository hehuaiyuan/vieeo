package com.vieeo.module.dao;

public class EntityDaoException extends Exception{

	private static final long serialVersionUID = -5932390127160240824L;

	public EntityDaoException(String message){
		super(message);
	}
	
	public EntityDaoException(String message,Exception e){
		super(message,e);
	}
}
