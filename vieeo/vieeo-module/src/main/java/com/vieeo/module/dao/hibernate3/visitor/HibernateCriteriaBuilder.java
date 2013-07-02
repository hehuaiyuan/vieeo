package com.vieeo.module.dao.hibernate3.visitor;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;

import com.vieeo.core.build.Builder;
import com.vieeo.core.condition.Condition;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expression;
import com.vieeo.core.condition.enums.AliasEnum;
import com.vieeo.core.condition.enums.ConditionEnum;
import com.vieeo.core.condition.enums.OrderEnum;
import com.vieeo.core.condition.expression.AliasExpression;
import com.vieeo.core.condition.expression.BetweenExpression;
import com.vieeo.core.condition.expression.InExpression;
import com.vieeo.core.condition.expression.NullExpression;
import com.vieeo.core.condition.expression.OrderExpression;
import com.vieeo.core.condition.expression.PageSelectExpression;
import com.vieeo.core.condition.expression.PropertyExpression;
import com.vieeo.module.context.ConditionsContext;
import com.vieeo.module.util.AliasUtils;

public class HibernateCriteriaBuilder implements Builder<DetachedCriteria,ConditionsContext>{
	
	@Override
	public DetachedCriteria build(ConditionsContext context) throws Exception {
		Conditions conditions = context.getConditions();
		Class<?> clazz = context.getClazz();
		
		if(conditions == null) return DetachedCriteria.forClass(clazz, AliasUtils.createAliasByClass(clazz));
		String clzAlias = StringUtils.isBlank(conditions.getAlias()) ? AliasUtils.createAliasByClass(clazz) : conditions.getAlias();
		DetachedCriteria dc = DetachedCriteria.forClass(clazz, clzAlias);
		
		//add 'alias' conditions
		if(conditions.hasAliasExpression()) {
			dc = processAliasConditions(conditions.getAliasCondition(),dc);
		}
		//add 'and' conditions
		if(conditions.hasAndExpression()) {
			dc = processAndConditions(conditions.getAndConditions(),dc);
		}
		//add 'or' conditions
		if(conditions.hasOrExpression()) {
			dc = processOrConditions(conditions.getOrConditions(),dc);
		}
		//add 'order' conditions
		if(conditions.hasOrderExpression()) {
			dc = processOrderConditions(conditions.getOrderConditions(),dc);
		}
		return dc;
	}

	/**
	 * 添加别名
	 * @param aliasConditions
	 * @param dc
	 * @return
	 * @throws Exception
	 */
	private DetachedCriteria processAliasConditions(Condition aliasConditions,DetachedCriteria dc) throws Exception{
		for (Expression expression : aliasConditions.getExpressions()) {
			if(AliasExpression.class.isInstance(expression)){
				dc = visit((AliasExpression)expression,dc);
			}
		}
		return dc;
	}
	
	/**
	 * 添加and条件
	 * @param aliasConditions
	 * @param dc
	 * @return
	 * @throws Exception
	 */
	private DetachedCriteria processAndConditions(Condition condition,DetachedCriteria dc)throws Exception{
		for (Expression expression : condition.getExpressions()) {
			Criterion criterion = visit(expression,dc);
			if(criterion != null) dc.add(criterion);
		}
		return dc;
	}
	
	private DetachedCriteria processOrConditions(List<Condition> orConditions,DetachedCriteria dc)throws Exception{
		for (Condition orCondition : orConditions) {
			Criterion criterion = visit(orCondition.getExpressions().get(0),dc);
			Criterion criterion1 = visit(orCondition.getExpressions().get(1),dc);
			dc.add(Restrictions.or(criterion, criterion1));
		}
		return dc;
	}
	
	private DetachedCriteria processOrderConditions(Condition orderConditions,DetachedCriteria dc)throws Exception{
		for (Expression expression : orderConditions.getExpressions()) {
			if(OrderExpression.class.isInstance(expression)){
				dc = visit((OrderExpression)expression,dc);
			}
		}
		return dc;
	}
	
	private Criterion visit(Expression visitor,DetachedCriteria dc) throws Exception{
		if(PropertyExpression.class.isInstance(visitor)){
			return visit((PropertyExpression)visitor,dc);
		}else if(InExpression.class.isInstance(visitor)){
			return visit((InExpression)visitor,dc);
		}else if(BetweenExpression.class.isInstance(visitor)){ 
			return visit((BetweenExpression)visitor,dc);
		}else if(PageSelectExpression.class.isInstance(visitor)) {
			return visit((PageSelectExpression)visitor,dc);
		}else if(NullExpression.class.isInstance(visitor)) 
			return visit((NullExpression)visitor,dc);
		return null;
	}
	
	private DetachedCriteria visit(AliasExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of AliasCondition can not be null");
		int joinType = JoinFragment.INNER_JOIN;
		if(AliasEnum.LEFT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = JoinFragment.LEFT_OUTER_JOIN;
		else if(AliasEnum.RIGHT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = JoinFragment.RIGHT_OUTER_JOIN;
		dc.createAlias(expression.getPropertyName(), expression.getAliasName(),joinType);
		return dc;
	}

	private Criterion visit(PropertyExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check()) throw new Exception("propertyName or value of Condition can not be null");
		if(ConditionEnum.EQ == expression.getType()) 
			return Restrictions.eq(expression.getPropertyName(),expression.getValue());
		else if(ConditionEnum.GE == expression.getType())
			return Restrictions.le(expression.getPropertyName(), expression.getValue());
		else if(ConditionEnum.LE == expression.getType())
			return Restrictions.ge(expression.getPropertyName(), expression.getValue());
		else if(ConditionEnum.LIKE == expression.getType())
			return Restrictions.like(expression.getPropertyName(), expression.getValue());
		return null;
	}
	
	private Criterion visit(NullExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of NullCondition can not be null");
		if(ConditionEnum.NOT_NULL == expression.getType()) 
			return Restrictions.isNotNull(expression.getPropertyName());
		else 
			return Restrictions.isNull(expression.getPropertyName());
	}
	
	private DetachedCriteria visit(OrderExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of OrderCondition can not be null");
		if(OrderEnum.ASC == expression.getSortType()) 
			dc.addOrder(Order.asc(expression.getPropertyName()));
		else 
			dc.addOrder(Order.desc(expression.getPropertyName()));
		return dc;
	}
	
	private Criterion visit(InExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check() || !expression.hasParams()) throw new Exception("propertyName or value of InCondition can not be null");
		if(ConditionEnum.IN == expression.getType()) 
			return Restrictions.in(expression.getPropertyName(), expression.getParams());
		else 
			return Restrictions.not(Restrictions.in(expression.getPropertyName(), expression.getParams()));
	}
	
	private Criterion visit(BetweenExpression expression,DetachedCriteria dc)throws Exception {
		if(!expression.check()) throw new Exception("propertyName or value of BetweenCondition can not be null");
		return Restrictions.between(expression.getPropertyName(), expression.getValues()[0],expression.getValues()[1]);
	}

}
