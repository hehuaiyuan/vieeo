package com.vieeo.core.condition.expression;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.HqlExpression;
import com.vieeo.core.condition.enums.ConditionEnum;

public class BetweenExpression extends HqlExpression{
	
	private String propertyName;

	private Object[] values;

	private ConditionEnum type;

	public BetweenExpression(String propertyName,Object[] values,ConditionEnum type){
		this.propertyName = propertyName;
		this.values = values;
		this.type = type;
	}

	@Override
	public Object[] getParams() {
		return values;
	}

	@Override
	public boolean hasParams() {
		return (values == null || values.length<=1 ) ? false : true ;
	}

	@Override
	public boolean check() {
		return (StringUtils.isBlank(propertyName) || (values == null || values.length<=1 ))? false : true ;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public ConditionEnum getType() {
		return type;
	}

	public void setType(ConditionEnum type) {
		this.type = type;
	}

}
