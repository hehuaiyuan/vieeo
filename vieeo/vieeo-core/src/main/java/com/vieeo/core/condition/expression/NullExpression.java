package com.vieeo.core.condition.expression;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.HqlExpression;
import com.vieeo.core.condition.enums.ConditionEnum;

public class NullExpression extends HqlExpression{
	
	private String propertyName;
	
	private ConditionEnum type;
	
	public NullExpression(String propertyName,ConditionEnum type){
		this.propertyName = propertyName;
		this.type = type;
	}

	@Override
	public Object[] getParams() {
		return null;
	}

	@Override
	public boolean hasParams() {
		return false;
	}

	@Override
	public boolean check() {
		return (StringUtils.isBlank(propertyName))? false : true ;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public ConditionEnum getType() {
		return type;
	}

	public void setType(ConditionEnum type) {
		this.type = type;
	}

}
