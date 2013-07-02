package com.vieeo.core.condition;


public interface Expression{
	
	public String getPropertyName();
	
	public Object[] getParams();
	
	public boolean hasParams();
	
	public boolean check();
}
