package com.vieeo.core.strategy;

public interface Visitor<T> {
	public <R extends Object> R visit(T visitor)throws Exception;
}
