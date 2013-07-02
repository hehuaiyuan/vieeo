package com.vieeo.core.condition.expression;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.Expression;
import com.vieeo.core.condition.enums.AliasEnum;

public class AliasExpression implements Expression{
	
	private AliasEnum type;
	
	private String propertyName;
	
	private String aliasName;
	
	public AliasExpression(String propertyName,String aliasName,AliasEnum type){
		this.propertyName = propertyName;
		this.aliasName = aliasName;
		this.type = type;
	}

	@Override
	public Object[] getParams() {
		return null;
	}

	@Override
	public boolean hasParams() {
		return (StringUtils.isBlank(propertyName) || StringUtils.isBlank(aliasName))?false : true;
	}

	@Override
	public boolean check() {
		return (StringUtils.isBlank(propertyName) || StringUtils.isBlank(aliasName))?false : true;
	}

	public AliasEnum getType() {
		return type;
	}

	public void setType(AliasEnum type) {
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

}
