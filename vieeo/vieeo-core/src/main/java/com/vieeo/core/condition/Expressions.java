package com.vieeo.core.condition;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.enums.AliasEnum;
import com.vieeo.core.condition.enums.ConditionEnum;
import com.vieeo.core.condition.enums.OrderEnum;
import com.vieeo.core.condition.expression.AliasExpression;
import com.vieeo.core.condition.expression.BetweenExpression;
import com.vieeo.core.condition.expression.InExpression;
import com.vieeo.core.condition.expression.NullExpression;
import com.vieeo.core.condition.expression.OrderExpression;
import com.vieeo.core.condition.expression.PropertyExpression;

public class Expressions {
	
	public static Expression alias(String propertyName,String alias,AliasEnum type){
		if(StringUtils.isBlank(propertyName) || alias == null) return null;
		return new AliasExpression(propertyName,alias,type == null ? AliasEnum.INNER_JOIN : type);
	}
	
	public static Expression eq(String propertyName,Object value){
		if(StringUtils.isBlank(propertyName) || value == null) return null;
		return new PropertyExpression(propertyName,value,ConditionEnum.EQ);
	}

	public static Expression le(String propertyName,Object value){
		if(StringUtils.isBlank(propertyName) || value == null) return null;
		return new PropertyExpression(propertyName,value,ConditionEnum.LE);
	}

	public static Expression ge(String propertyName,Object value){
		if(StringUtils.isBlank(propertyName) || value == null) return null;
		return new PropertyExpression(propertyName,value,ConditionEnum.GE);
	}

	public static Expression like(String propertyName,Object value){
		if(StringUtils.isBlank(propertyName) || value == null) return null;
		return new PropertyExpression(propertyName,value,ConditionEnum.LIKE);
	}

	public static Expression in(String propertyName,Object... value){
		if(StringUtils.isBlank(propertyName) || value == null || value.length<=0) return null;
		return new InExpression(propertyName,value,ConditionEnum.IN);
	}
	
	public static Expression in(String propertyName,String... value){
		if(StringUtils.isBlank(propertyName) || value == null || value.length<=0) return null;
		return new InExpression(propertyName,value,ConditionEnum.IN);
	}
	
	public static Expression isNull(String propertyName){
		if(StringUtils.isBlank(propertyName)) return null;
		return new NullExpression(propertyName,ConditionEnum.NULL);
	}
	
	public static Expression isNotNull(String propertyName){
		if(StringUtils.isBlank(propertyName)) return null;
		return new NullExpression(propertyName,ConditionEnum.NOT_NULL);
	}

	public static Expression notIn(String propertyName,Object... value){
		if(StringUtils.isBlank(propertyName) || value == null || value.length<=0) return null;
		return new InExpression(propertyName,value,ConditionEnum.NOT_IN);
	}
	
	public static Expression between(String propertyName,Object... value){
		if(StringUtils.isBlank(propertyName) || value == null || value.length<=0) return null;
		return new BetweenExpression(propertyName,value,ConditionEnum.BETWEEN);
	}
	
	public static Expression orderAsc(String propertyName){
		if(StringUtils.isBlank(propertyName)) return null;
		return new OrderExpression(propertyName,OrderEnum.ASC);
	}
	
	public static Expression orderDesc(String propertyName){
		if(StringUtils.isBlank(propertyName)) return null;
		return new OrderExpression(propertyName,OrderEnum.DESC);
	}
	
	public static Expression order(String propertyName,OrderEnum sortType){
		if(StringUtils.isBlank(propertyName) || sortType==null) return null;
		return new OrderExpression(propertyName,sortType);
	}
}
