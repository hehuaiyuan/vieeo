package com.vieeo.module.dao.hibernate3.visitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.condition.Condition;
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
import com.vieeo.core.domain.Entity;
import com.vieeo.core.strategy.Visitor;
import com.vieeo.module.util.AliasUtils;
public class HQLVisitor implements Visitor<Condition>{
	
	public static String FROM = "FROM ";
	public static String ORDER_BY = " ORDER BY ";
	public static String WHERE = " WHERE ";

	//private Class<? extends Entity> clazz;

	private String className;

	private String alias;

	private String type;
	
	private boolean where =false;
	
	public HQLVisitor(Class<? extends Entity> clazz,String alias) {
		//this.clazz = clazz;
		this.className = getClassName(clazz);
		this.alias = StringUtils.isBlank(alias) ? getClassAlias(clazz) : alias;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String visit(Condition visitor)throws Exception {
		StringBuilder hql = new StringBuilder();
		this.type = visitor.getConditionType();
		if(ConditionType.BODY.getCode() == visitor.getConditionType()) {
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression)); }
		}else if(ConditionType.ALIAS.getCode() == visitor.getConditionType()) {
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression)); }
		}else if(ConditionType.AND.getCode() == visitor.getConditionType()) {
			appendWhere(hql);
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression)); }
		}else if(ConditionType.OR.getCode() == visitor.getConditionType()){
			appendWhere(hql);
			hql.append(" ").append(ConditionType.AND.getCode()).append(" (");
			for (Expression expression : visitor.getExpressions()) { hql.append(visit(expression)); }
			hql.delete(hql.length()-4, hql.length()-1);
			hql.append(")");
		}else if(ConditionType.ORDER.getCode() == visitor.getConditionType()){
			hql.append(" ").append(ConditionType.ORDER.getCode());
			for (int i=0;i<visitor.getExpressions().size();i++) { 
				Expression expression = visitor.getExpressions().get(i);
				hql.append(visit(expression)); 
				if(i<visitor.getExpressions().size()-1) hql.append(" ,");
			}
		}
		return hql.toString();
	}
	
	private void appendWhere(StringBuilder hql){
		if(!where) hql.append(" WHERE 1=1");
		where = true;
	}
	
	private String visit(Expression visitor) throws Exception{
		if(SelectExpression.class.isInstance(visitor)) {
			return visit((SelectExpression)visitor);
		}else if(UpdateExpression.class.isInstance(visitor)){
			return visit((UpdateExpression)visitor);
		}else if(DeleteExpression.class.isInstance(visitor)){
			return visit((DeleteExpression)visitor);
		}else if(AliasExpression.class.isInstance(visitor)){
			return visit((AliasExpression)visitor);
		}else if(PropertyExpression.class.isInstance(visitor)){
			return visit((PropertyExpression)visitor);
		}else if(InExpression.class.isInstance(visitor)){
			return visit((InExpression)visitor);
		}else if(BetweenExpression.class.isInstance(visitor)){ 
			return visit((BetweenExpression)visitor);
		}else if(PageSelectExpression.class.isInstance(visitor)) {
			return visit((PageSelectExpression)visitor);
		}else if(OrderExpression.class.isInstance(visitor)){
			return visit((OrderExpression)visitor);
		}else if(PageSelectWithoutClazzExpression.class.isInstance(visitor)){
			return visit((PageSelectWithoutClazzExpression)visitor);
		}else if(NullExpression.class.isInstance(visitor)) 
			return visit((NullExpression)visitor);
		return null;
	}

	private String visit(SelectExpression expression) throws Exception{
		return new StringBuilder("SELECT ").append(alias).append(" FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}

	private String visit(PageSelectExpression expression) throws Exception{
		return new StringBuilder("SELECT COUNT(*) FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}

	private String visit(UpdateExpression expression) throws Exception{
		return new StringBuilder("UPDATE ").append(className).append(" ").append(alias).append(" ").toString();
	}

	private String visit(DeleteExpression expression)throws Exception {
		return new StringBuilder("DELETE FROM ").append(className).append(" AS ").append(alias).append(" ").toString();
	}
	
	private String visit(AliasExpression expression)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of AliasCondition can not be null");
		String joinType = ConditionEnum.INNER_JOIN.getValue();
		if(AliasEnum.LEFT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = ConditionEnum.LEFT_JOIN.getValue();
		else if(AliasEnum.RIGHT_JOIN.getCode().equals(expression.getType().getCode()))
			joinType = ConditionEnum.RIGHT_JOIN.getValue();
		return new StringBuilder(joinType).append(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" AS ").append(expression.getAliasName()).append(" ").toString();
	}

	private String visit(PropertyExpression expression)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(new StringBuilder(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()).append(" ?"));
	}
	
	private String visit(NullExpression expression)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(new StringBuilder(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()));
	}
	
	private String visit(OrderExpression expression)throws Exception {
		if(!expression.check()) throw new Exception("propertyName or value can not be null");
		return new StringBuilder(" ").append(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" ").append(expression.getSortType().getSortType()).toString();
	}
	
	private String visit(PageSelectWithoutClazzExpression expression)throws Exception {
		boolean needDistinct = expression.getQueryHql().toLowerCase().startsWith("select distinct ");
		return appendSelectCount(removeSelect(removeOrderBy(expression.getQueryHql())), needDistinct);
	}

	private String visit(InExpression expression)throws Exception {
		if(!expression.check() || !expression.hasParams()) throw new Exception("propertyName or value can not be null");
		StringBuilder hql = new StringBuilder(" (").append(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" ").append(expression.getType().getValue()).append(" (");
		Object [] values = expression.getValues();
		for (int i=0;i<values.length;i++) {
			hql.append("?");
			if(i != values.length-1) hql.append(",");
		}
		hql.append("))");
		return createString(hql);
	}
	
	private String visit(BetweenExpression expression)throws Exception {
		if(!expression.check()) throw new Exception("propertyName of Condition can not be null");
		return createString(new StringBuilder("(").append(getAlias(expression.getPropertyName())).append(expression.getPropertyName()).append(" ")
					.append(expression.getType().getValue()).append(" ? ").append(ConditionType.AND.getCode()).append(" ?").append(")"));
	}

	private String getClassName(Class<? extends Entity> clazz) {
		return clazz.getName();
	}

	private String createString(StringBuilder hql){
		if(ConditionType.AND.getCode() == type) {
			return new StringBuilder(" ").append(type).append(" ").append(hql).toString();
		} else {
			return hql.append(" ").append(type).append(" ").toString();
		}
	}

	private String getClassAlias(Class<? extends Entity> clazz){
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
	
	private String getAlias(String propertyName){
		return AliasUtils.isAliasExists(propertyName) ? "" : alias+".";
	}

}
