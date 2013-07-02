package com.vieeo.core.condition;

import com.vieeo.core.condition.enums.ConditionType;

/**
 * 排序条件
 * @author roy.he
 *
 */
public class OrderCondition extends AbstractCondition{

	private static final long serialVersionUID = 2669878058100386783L;

	@Override
	public String getConditionType() {
		return ConditionType.ORDER.getCode();
	}

	@Override
	public void addExpressions(Expression... expressions) {
		if(expressions == null) return ;
		for (Expression expression : expressions) {
			getExpressions().add(expression);
		}
	}

}
