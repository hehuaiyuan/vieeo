package com.vieeo.module.dao.hibernate3.visitor;

import java.util.List;

import com.vieeo.core.condition.BodyCondition;
import com.vieeo.core.condition.Condition;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expression;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.strategy.Visitor;

public class HibernateVisitor implements Visitor<Conditions>{

	private Expression baseExpression;

	private Class<? extends Entity> clazz;

	private Visitor<Condition> hqlVisitor;

	private boolean init = false;
	
	public HibernateVisitor(Expression baseExpression,Class<? extends Entity> clazz,String alias) {
		this.baseExpression = baseExpression;
		this.clazz = clazz;
		hqlVisitor = new HQLVisitor(clazz,alias);
	}

	private void initHql(StringBuilder hql) throws Exception{
		BodyCondition condition = new BodyCondition();
		condition.addExpressions(baseExpression);
		hql.append(hqlVisitor.visit(condition));
		init = true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String visit(Conditions visitor)throws Exception {
		try {
			if(baseExpression == null) throw new Exception("HQL error : baseExpression can not be null");
			StringBuilder hql = new StringBuilder();
			if(!init) initHql(hql);
			if(visitor == null) return hql.toString();
			if(visitor.hasAliasExpression()) {
				hql.append(hqlVisitor.visit(visitor.getAliasCondition()));
			}
			//add 'and' conditions
			if(visitor.hasAndExpression()) {
				hql.append(hqlVisitor.visit(visitor.getAndConditions()));
			}
			//add 'or' conditions
			if(visitor.hasOrExpression()) {
				List<Condition> orConditions = visitor.getOrConditions();
				for (Condition orCondition : orConditions) { hql.append(hqlVisitor.visit(orCondition)); }
			}
			//add 'order' conditions
			if(visitor.hasOrderExpression()) {
				hql.append(hqlVisitor.visit(visitor.getOrderConditions())); 
			}
			return hql.toString();
		}catch(Exception e) {
			throw new Exception("hql error happened:",e);
		}
	}

	protected Class<? extends Entity> getClazz() {
		return clazz;
	}

	protected void setClazz(Class<? extends Entity> clazz) {
		this.clazz = clazz;
	}

}
