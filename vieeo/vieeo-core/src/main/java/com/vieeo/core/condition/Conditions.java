package com.vieeo.core.condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vieeo.core.condition.enums.AliasEnum;

public class Conditions implements Serializable{

	private static final long serialVersionUID = -7364079776612923068L;
	
	private AliasCondition aliasCondition;

	private AndCondition andConditions;

	private List<Condition> orConditions;
	
	private OrderCondition orderConditions;
	
	private String alias;

	private List<Object> values = new ArrayList<Object>();
	
	private Conditions(){}
	
	public List<Condition> getConditions(){
		List<Condition> conditions = new ArrayList<Condition>();
		if(hasAliasExpression()) conditions.add(aliasCondition);
		if(hasAndExpression()) conditions.add(andConditions);
		if(hasOrExpression()){
			for (Condition condition : orConditions) {
				conditions.add(condition);
			}
		}
		if(hasOrderExpression()) conditions.add(orderConditions);
		return conditions;
	}

	public AndCondition getAndConditions() {
		return andConditions;
	}
	
	public Conditions createAlias(String propertyName,String alias){
		if(aliasCondition == null) aliasCondition = new AliasCondition();
		Expression expression = Expressions.alias(propertyName, alias, null);
		if(expression == null) return this;
		aliasCondition.addExpressions(expression);
		return this;
	}
	
	public Conditions createAlias(String propertyName,String alias,AliasEnum type){
		if(aliasCondition == null) aliasCondition = new AliasCondition();
		Expression expression = Expressions.alias(propertyName, alias, type);
		if(expression == null) return this;
		aliasCondition.addExpressions(expression);
		return this;
	}

	public Conditions and(Expression expression){
		if(andConditions == null) andConditions = new AndCondition();
		if(expression == null) return this;
		andConditions.getExpressions().add(expression);
		addValues(expression.getParams());
		return this;
	}

	public Conditions or(Expression... expressions){
		if(orConditions == null) orConditions = new ArrayList<Condition>();
		if(expressions == null || expressions.length <=0) return this;
		OrCondition orCondition = new OrCondition();
		for (Expression expression : expressions) {
			orCondition.addExpressions(expression);
			addValues(expression.getParams());
		}
		orConditions.add(orCondition);
		return this;
	}
	
	public Conditions order(Expression... expressions) {
		if(orderConditions == null) orderConditions = new OrderCondition();
		for (Expression expression : expressions) {
			orderConditions.addExpressions(expression);
		}
		return this;
	}

	protected void addValues(Object[] values){
		if(values == null || values.length <= 0) return ;
		for (Object object : values) {
			getValues().add(object);
		}
	}

	public List<Expression> getAndExpressions() {
		return andConditions.getExpressions();
	}

	public List<Condition> getOrConditions() {
		return orConditions;
	}

	public Condition getOrderConditions() {
		return orderConditions;
	}

	public static Conditions create(){
		return new Conditions();
	}
	
	public static Conditions create(String alias){
		Conditions conditions = new Conditions();
		conditions.setAlias(alias);
		return conditions;
	}
	
	public boolean hasAliasExpression(){
		return aliasCondition != null && aliasCondition.getExpressions() != null && aliasCondition.getExpressions().size() > 0 ? true : false;
	}

	public boolean hasAndExpression(){
		return andConditions != null && andConditions.getExpressions() != null && andConditions.getExpressions().size() > 0 ? true : false;
	}
	
	public boolean hasOrderExpression(){
		return orderConditions != null && orderConditions.getExpressions() != null && orderConditions.getExpressions().size() > 0 ? true : false;
	}

	public boolean hasOrExpression(){
		return orConditions != null && orConditions.size() > 0 ? true : false;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public Condition getAliasCondition() {
		return aliasCondition;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
