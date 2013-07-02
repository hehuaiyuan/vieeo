package com.vieeo.core.condition;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCondition implements Condition {

	private static final long serialVersionUID = -4712609823072657787L;
	
	private List<Expression> expressions = new ArrayList<Expression>();

	@Override
	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

}
