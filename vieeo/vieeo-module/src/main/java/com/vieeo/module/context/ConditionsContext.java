package com.vieeo.module.context;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expression;

/**
 * 条件解析上下文
 * @author roy.he
 *
 */
public class ConditionsContext {
	
	private Expression baseExpression;
	
	private Conditions conditions;
	
	private Class<?> clazz;
	
	public static ConditionsContext create(Class<?> clazz,Conditions conditions,Expression baseExpression){
		ConditionsContext context = new ConditionsContext();
		context.setConditions(conditions);
		context.setClazz(clazz);
		context.setBaseExpression(baseExpression);
		return context;
	}

	public Expression getBaseExpression() {
		return baseExpression;
	}

	public void setBaseExpression(Expression baseExpression) {
		this.baseExpression = baseExpression;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
