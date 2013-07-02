package com.vieeo.core.condition;

import com.vieeo.core.condition.enums.ConditionType;

public class AliasCondition extends AbstractCondition{
	
	private static final long serialVersionUID = 844131828441033627L;
	
	@Override
	public String getConditionType() {
		return ConditionType.ALIAS.getCode();
	}

	@Override
	public void addExpressions(Expression... expressions) {
		if(expressions == null) return ;
		for (Expression expression : expressions) {
			getExpressions().add(expression);
		}
	}

}
