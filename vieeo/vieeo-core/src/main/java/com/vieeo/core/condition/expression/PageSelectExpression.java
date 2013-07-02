package com.vieeo.core.condition.expression;

import com.vieeo.core.condition.HqlExpression;
import com.vieeo.core.domain.Entity;

public class PageSelectExpression extends HqlExpression{

	public PageSelectExpression(Class<? extends Entity> clazz) {
		super(clazz);
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
		return true;
	}

	@Override
	public String getPropertyName() {
		return null;
	}

}
