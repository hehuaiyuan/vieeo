package com.vieeo.test.conditions;

import junit.framework.TestCase;

import com.vieeo.core.build.Builder;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.condition.expression.SelectExpression;
import com.vieeo.module.context.ConditionsContext;
import com.vieeo.module.dao.hibernate3.visitor.HQLBuilder;

public class ConditionsTest extends TestCase {

	public void testHql() throws Exception{
		Conditions conditions = Conditions.create("t").and(Expressions.eq("t.name", "hehuaiyuan")).and(Expressions.like("t.description", "aa"));
		conditions.and(Expressions.eq("d.name", "销售部"));
		conditions.order(Expressions.orderDesc("t.description"));
		conditions.createAlias("t.role", "r");
		conditions.createAlias("t.dept", "d");
		
		conditions.or(Expressions.between("t.startDate", "2010-05-01","2010-05-31"),Expressions.between("t.endDate", "2010-05-01","2010-05-31"));
		conditions.or(Expressions.eq("name", "aaa"),Expressions.eq("name", "bbb"));
		conditions.or(Expressions.eq("name", "ccc"),Expressions.like("description", "aaa"));
		conditions.order(Expressions.orderAsc("name"));
		
		Builder<String,ConditionsContext> builder = new HQLBuilder();
		System.out.println(builder.build(ConditionsContext.create(User.class, conditions, new SelectExpression(User.class))));
	}
}
