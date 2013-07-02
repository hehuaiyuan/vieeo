package com.vieeo.core.condition;

import java.io.Serializable;
import java.util.List;
/**
 * 条件接口
 * @author hehy
 */
public interface Condition extends Serializable{

	public String getConditionType();

	public List<Expression> getExpressions();

	public void addExpressions(Expression ... expressions);

}
