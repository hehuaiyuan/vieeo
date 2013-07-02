package com.vieeo.core.condition;

import com.vieeo.core.condition.enums.ConditionType;


public class OrCondition extends AbstractCondition{

	private static final long serialVersionUID = 1313144478627353506L;

	@Override
	public String getConditionType() {
		return ConditionType.OR.getCode();
	}

	public void addExpressions(Expression ... expressions){
		if(expressions == null) return ;
		for (Expression expression : expressions) {
			getExpressions().add(expression);
		}
	}
}
