package com.vieeo.core.condition;

import com.vieeo.core.condition.enums.ConditionType;



public class AndCondition extends AbstractCondition{
	
	private static final long serialVersionUID = 1851767966744535693L;

	@Override
	public String getConditionType() {
		return ConditionType.AND.getCode();
	}

	@Override
	public void addExpressions(Expression... expressions) {
		if(expressions == null) return ;
		for (Expression expression : expressions) {
			getExpressions().add(expression);
		}
	}

}
