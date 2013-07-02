package com.vieeo.core.domain.support;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.vieeo.core.domain.BizEntity;
import com.vieeo.core.domain.Entity;

public class BizMapEntity extends HashMap<String,Object> implements BizEntity{

	private static final long serialVersionUID = -6946665110695826362L;

	@Override
	public Object get(String key) {
		return super.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(String key) {
		return (T)get(key);
	}

	@Override
	public String getStringValue(String key) {
		Object value = get(key);
		return isInstance(value,String.class) ? (String)value : null;
	}

	@Override
	public String getStringValue(String key, String def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,String.class) ? (String)value : def;
	}

	@Override
	public Integer getIntegerValue(String key) {
		Object value = get(key);
		return isInstance(value,Integer.class) ? (Integer)value : null;
	}

	@Override
	public Integer getIntegerValue(String key, Integer def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,Integer.class) ? (Integer)value : def;
	}

	@Override
	public Long getLongValue(String key) {
		Object value = get(key);
		return isInstance(value,Long.class) ? (Long)value : null;
	}

	@Override
	public Long getLongValue(String key, Long def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,Long.class) ? (Long)value : def;
	}

	@Override
	public Double getDoubleValue(String key) {
		Object value = get(key);
		return isInstance(value,Double.class) ? (Double)value : null;
	}

	@Override
	public Double getDoubleValue(String key, Double def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,Double.class) ? (Double)value : def;
	}

	@Override
	public Date getDateValue(String key) {
		Object value = get(key);
		return isInstance(value,Date.class) ? (Date)value : null;
	}

	@Override
	public Date getDateValue(String key, Date date) {
		Object value = get(key);
		if(value == null) return date;
		return isInstance(value,Date.class) ? (Date)value : date;
	}

	@Override
	public Boolean getBooleanValue(String key, Boolean def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,Boolean.class) ? (Boolean)value : def;
	}

	@Override
	public BigDecimal getBigDecimalValue(String key) {
		Object value = get(key);
		return isInstance(value,BigDecimal.class) ? (BigDecimal)value : null;
	}

	@Override
	public BigDecimal getBigDecimalValue(String key, BigDecimal def) {
		Object value = get(key);
		if(value == null) return def;
		return isInstance(value,BigDecimal.class) ? (BigDecimal)value : def;
	}

	@SuppressWarnings("rawtypes")
	private boolean isInstance(Object value,Class type){
		if(value == null) return false;
		return type.isInstance(value);
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		put(Entity.EntityConext.ENTITY_DATACREATED.getName(), dateCreated);
	}

	@Override
	public void setDateLastModified(Date dateLastModified) {
		put(Entity.EntityConext.ENTITY_DATALASTMODIFED.getName(), dateLastModified);
	}

	@Override
	public void setUserCreated(String userCreated) {
		put(Entity.EntityConext.ENTITY_USERCREATED.getName(), userCreated);
	}

	@Override
	public void setUserLastModified(String userLastModifed) {
		put(Entity.EntityConext.ENTITY_USERLASTMODIFED.getName(), userLastModifed);
	}

	@Override
	public Object get(int index) {
		return super.get(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(int index) {
		return (T)get(index);
	}

	@Override
	public <PK extends Serializable> PK getId() {
		return null;
	}

	@Override
	public void set(String key, Object value) {
		super.put(key, value);
	}

}
