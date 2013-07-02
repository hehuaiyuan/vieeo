package com.vieeo.core.condition.expression;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.HqlExpression;
import com.vieeo.core.condition.enums.ConditionEnum;

public class PropertyExpression extends HqlExpression{
	
	private String propertyName;
	
	private Object value;
	
	private ConditionEnum type;
	
	public PropertyExpression(String propertyName,Object value,ConditionEnum type){
		this.propertyName = propertyName;
		this.value = value;
		this.type = type;
	}

	@Override
	public boolean check() {
		return (StringUtils.isBlank(propertyName))? false : true ;
		//return (StringUtils.isBlank(propertyName) || value == null)? false : true ;
	}

	@Override
	public Object[] getParams() {
		return new Object[]{value};
	}

	@Override
	public boolean hasParams() {
		return value == null ? false : true ;
	}
	
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ConditionEnum getType() {
		return type;
	}

	public void setType(ConditionEnum type) {
		this.type = type;
	}
}
