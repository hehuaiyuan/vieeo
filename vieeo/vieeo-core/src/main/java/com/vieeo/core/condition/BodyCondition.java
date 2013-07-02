package com.vieeo.core.condition;

import com.vieeo.core.condition.enums.ConditionType;



public class BodyCondition extends AbstractCondition{

	private static final long serialVersionUID = -1874504953996996128L;

	@Override
	public String getConditionType() {
		return ConditionType.BODY.getCode();
	}

	@Override
	public void addExpressions(Expression... expressions) {
		if(expressions == null) return ;
		for (Expression expression : expressions) {
			getExpressions().add(expression);
		}
	}

}
