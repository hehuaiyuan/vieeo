package com.vieeo.core.strategy;


public interface Strategy<T> {
	public <R extends Object> R accept(Visitor<T> obj)throws Exception;

}
