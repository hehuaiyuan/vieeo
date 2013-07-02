package com.vieeo.module.dao.hibernate3.visitor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.build.Builder;
import com.vieeo.core.condition.Condition;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expression;
import com.vieeo.core.condition.enums.AliasEnum;
import com.vieeo.core.condition.enums.ConditionEnum;
import com.vieeo.core.condition.enums.ConditionType;
import com.vieeo.core.condition.expression.AliasExpression;
import com.vieeo.core.condition.expression.BetweenExpression;
import com.vieeo.core.condition.expression.DeleteExpression;
import com.vieeo.core.condition.expression.InExpression;
import com.vieeo.core.condition.expression.NullExpression;
import com.vieeo.core.condition.expression.OrderExpression;
import com.vieeo.core.condition.expression.PageSelectExpression;
import com.vieeo.core.condition.expression.PageSelectWithoutClazzExpression;
import com.vieeo.core.condition.expression.PropertyExpression;
import com.vieeo.core.condition.expression.SelectExpression;
import com.vieeo.core.condition.expression.UpdateExpression;
import com.vieeo.module.context.ConditionsContext;
import com.vieeo.module.util.AliasUtils;
import com.vieeo.util.ListUtils;

public class HQLBuilder implements Builder<String,ConditionsContext>{
	
	public static String FROM = "FROM ";
	public static String ORDER_BY = " ORDER BY ";
	public static String WHERE = " WHERE ";

	@Override
	public String build(ConditionsContext context) throws Exception {
		StringBuilder hql = new StringBuilder();
		Conditions conditionsContext = context.getConditions();
		Class<?> clazz = context.getClazz();
		//初始化要操作的完成类名和对应别名
		String className = getClassName(clazz);
		String alias = (conditionsContext == null || StringUtils.isBlank(conditionsContext.getAlias()))
											? getClassAlias(clazz) : conditionsContext.getAlias();
		//初始化HQL主体
		if(context.getBaseExpression() != null) hql.append(visit(context.getBaseExpression(),className,alias,null));
		//没有条件直接返回
		if(conditionsContext == null || ListUtils.isEmpty(conditionsContext.getConditions())) return hql.toString();
		//添加条件,包括别名,and,or,order
		List<Condition> conditions = conditionsContext.getConditions();
		boolean where = false;
		for (Condition condition : conditions) {
			if((ConditionType.AND.getCode().equals(condition.getConditionType()) 
					|| ConditionType.OR.getCode().equals(condition.getConditionType())) && !where){
				hql.append(" WHERE 1=1");
			}
			hql.append(visit(condition,className,alias));
		}
		return hql.toString();
	}
	
	//处理condition
	private String visit(Condition visitor,String className,String alias)throws Exception {
		StringBuilder hql = new StringBuilder();
		String type = visitor.getConditionType();
		if(ConditionType.ALIAS.getCode() == visitor.getConditionType()) {
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression,className,alias,type)); }
		}else if(ConditionType.AND.getCode() == visitor.getConditionType()) {
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression,className,alias,type)); }
		}else if(ConditionType.OR.getCode() == visitor.getConditionType()){
			hql.append(" ").append(ConditionType.AND.getCode()).append(" (");
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression,className,alias,type)); }
			hql.delete(hql.length()-4, hql.length()-1);
			hql.append(")");
		}else if(ConditionType.ORDER.getCode() == visitor.getConditionType()){
			hql.append(" ").append(ConditionType.ORDER.getCode());
			for (int i=0;i<visitor.getExpressions().size();i++) { 
				Expression expression = visitor.getExpressions().get(i);
				hql.append(visit(expression,className,alias,type)); 
				if(i<visitor.getExpressions().size()-1) hql.append(" ,");
			}
		}
		return hql.toString();
	}
	
	//处理expression
	private String visit(Expression visitor,String className,String alias,String conditionType) throws Exception{
		if(SelectExpression.class.isInstance(visitor)) {
			return visit((SelectExpression)visitor,className,alias);
		}else if(PageSelectExpression.class.isInstance(visitor)) {
			return visit((PageSelectExpression)visitor,className,alias);
		}else if(PageSelectWithoutClazzExpression.class.isInstance(visitor)){
			return visit((PageSelectWithoutClazzExpression)visitor);
		}else if(UpdateExpression.class.isInstance(visitor)){
			return visit((UpdateExpression)visitor,className,alias);
		}else if(DeleteExpression.class.isInstance(visitor)){
			return visit((DeleteExpression)visitor,className,alias);
		}else if(AliasExpression.class.isInstance(visitor)){
			return visit((AliasExpression)visitor,alias);
		}else if(PropertyExpression.class.isInstance(visitor)){
			return visit((PropertyExpression)visitor,alias,conditionType);
		}else if(InExpression.class.isInstance(visitor)){
			return visit((InExpression)visitor,alias,conditionType);
		}else if(BetweenExpression.class.isInstance(visitor)){ 
			return visit((BetweenExpression)visitor,alias,conditionType);
		}else if(OrderExpression.class.isInstance(visitor)){
			return visit((OrderExpression)visitor,alias);
		}else if(NullExpression.class.isInstance(visitor)) 
			return visit((NullExpression)visitor,alias,conditionType);
		return null;
	}

	private String visit(SelectExpression expression,String className,String alias) throws Exception{
		return new StringBuilder("SELECT ").append(alias).append(" FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}

	private String visit(PageSelectExpression expression,String className,String alias) throws Exception{
		return new StringBuilder("SELECT COUNT(*) FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}

	private String visit(UpdateExpression expression,String className,String alias) throws Exception{
		return new StringBuilder("UPDATE ").append(className).append(" ").append(alias).append(" ").toString();
	}

	private String visit(DeleteExpression expression,String className,String alias)throws Exception {
		return new StringBuilder("DELETE FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}
	
	private String visit(AliasExpression expression,String alias)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of AliasCondition can not be null");
		String joinType = ConditionEnum.INNER_JOIN.getValue();
		if(AliasEnum.LEFT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = ConditionEnum.LEFT_JOIN.getValue();
		else if(AliasEnum.RIGHT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = ConditionEnum.RIGHT_JOIN.getValue();
		return new StringBuilder(joinType).append(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" AS ").append(expression.getAliasName()).append(" ").toString();
	}

	private String visit(PropertyExpression expression,String alias,String conditionType)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(conditionType,new StringBuilder(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()).append(" ?"));
	}
	
	private String visit(NullExpression expression,String alias,String conditionType)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(conditionType,new StringBuilder(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()));
	}
	
	private String visit(OrderExpression expression,String alias)throws Exception {
		if(!expression.check()) throw new Exception("propertyName or value can not be null");
		return new StringBuilder(" ").append(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" ").append(expression.getSortType().getSortType()).toString();
	}
	
	private String visit(PageSelectWithoutClazzExpression expression)throws Exception {
		boolean needDistinct = expression.getQueryHql().toLowerCase().startsWith("select distinct ");
		return appendSelectCount(removeSelect(removeOrderBy(expression.getQueryHql())), needDistinct);
	}

	private String visit(InExpression expression,String alias,String conditionType)throws Exception {
		if(!expression.check() || !expression.hasParams()) throw new Exception("propertyName or value can not be null");
		StringBuilder hql = new StringBuilder(" (").append(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()).append(" (");
		Object [] values = expression.getValues();
		for (int i=0;i<values.length;i++) {
			hql.append("?");
			if(i != values.length-1) hql.append(",");
		}
		hql.append("))");
		return createString(conditionType,hql);
	}
	
	private String visit(BetweenExpression expression,String alias,String conditionType)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(conditionType,new StringBuilder("(").append(getAlias(expression.getPropertyName(),alias)).append(expression.getPropertyName()).append(" ")
					.append(expression.getType().getValue()).append(" ? ").append(ConditionType.AND.getCode()).append(" ?").append(")"));
	}

	private String getClassName(Class<?> clazz) {
		return clazz.getName();
	}

	private String createString(String type,StringBuilder hql){
		if(ConditionType.AND.getCode() == type) {
			return new StringBuilder(" ").append(type).append(" ").append(hql).toString();
		} else {
			return hql.append(" ").append(type).append(" ").toString();
		}
	}

	private String getClassAlias(Class<?> clazz){
		return AliasUtils.createAliasByClass(clazz);
	}
	
	private String removeOrderBy(String jpaQl) {
		int orderByIndex = jpaQl.toUpperCase().lastIndexOf(ORDER_BY);
		
		if(orderByIndex > 0) {
			jpaQl = jpaQl.substring(0, orderByIndex);
		}
		
		return jpaQl;
	}
	
	private String removeSelect(String jpaQl) {
		String tmpJpaQl = jpaQl.toUpperCase();
		
		Pattern p = Pattern.compile(FROM);
	    Matcher m = p.matcher(tmpJpaQl);
		
	    while(m.find()){
	    	int start = m.start();
	    	int end = m.end();
	    	
	    	String leftPart = tmpJpaQl.substring(0, start);
	    	String rightPart = tmpJpaQl.substring(end);
	    	
	    	int llp = StringUtils.countMatches(leftPart, "(");
	    	int lrp = StringUtils.countMatches(leftPart, ")");
	    	
	    	int rlp = StringUtils.countMatches(rightPart, "(");
	    	int rrp = StringUtils.countMatches(rightPart, ")");
	    	
	    	if(llp == lrp && rlp == rrp){
	    		jpaQl = jpaQl.substring(start);
	    	}
	    }
	    
		return jpaQl;
	}
	
	private String appendSelectCount(String jpaQl, boolean needDistinct) {
		StringBuilder count = new StringBuilder("SELECT COUNT(");
		if (needDistinct) count.append("DISTINCT ");
		count.append("*) ");
		return count.append(jpaQl).toString();
	}
	
	private String getAlias(String propertyName,String alias){
		return AliasUtils.isAliasExists(propertyName) ? "" : alias+".";
	}

}
