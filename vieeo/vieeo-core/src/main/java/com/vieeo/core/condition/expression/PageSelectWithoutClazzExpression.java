package com.vieeo.core.condition.expression;

import com.vieeo.core.condition.HqlExpression;

/**
 * 执行特殊hql分页时使用的hql条件构造对象
 * @author roy.he
 *
 */
public class PageSelectWithoutClazzExpression extends HqlExpression{
	
	//查询hql
	private String queryHql;

	public PageSelectWithoutClazzExpression(String queryHql) {
		//super(clazz);
		this.queryHql = queryHql;
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

	public String getQueryHql() {
		return queryHql;
	}

	public void setQueryHql(String queryHql) {
		this.queryHql = queryHql;
	}

	@Override
	public String getPropertyName() {
		return null;
	}

}
