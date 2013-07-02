package com.vieeo.core.condition.expression;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.HqlExpression;
import com.vieeo.core.condition.enums.OrderEnum;

/**
 * 排序条件
 * @author roy.he
 *
 */
public class OrderExpression extends HqlExpression{
	
	private String propertyName;
	
	private OrderEnum sortType;
	
	public OrderExpression(String propertyName,OrderEnum sortType) {
		this.propertyName = propertyName;
		this.sortType = sortType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public OrderEnum getSortType() {
		return sortType;
	}

	public void setSortType(OrderEnum sortType) {
		this.sortType = sortType;
	}

	@Override
	public Object[] getParams() {
		return new Object[]{sortType};
	}

	@Override
	public boolean hasParams() {
		return sortType != null ;
	}

	@Override
	public boolean check() {
		return (StringUtils.isBlank(propertyName) || sortType == null)? false : true ;
	}

}
