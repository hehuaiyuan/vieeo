package com.vieeo.core.condition;

import com.vieeo.core.domain.Entity;

public abstract class HqlExpression implements Expression{

	protected Class<? extends Entity> clazz;

	protected HqlExpression(){}

	protected HqlExpression(Class<? extends Entity> clazz) {
		this.clazz = clazz;
	}

	public Class<? extends Entity> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends Entity> clazz) {
		this.clazz = clazz;
	}

}
