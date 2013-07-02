package com.vieeo.core.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 业务VO对象,保存业务流程中产生的非数据库实体记录值
 * @author roy.he
 *
 */
public interface BizEntity extends Entity,Map<String,Object>{

	public Object get(String key);

	public Object get(int index);

	public <T> T getValue(String key);

	public <T> T getValue(int index);

	public String getStringValue(String key);

	public String getStringValue(String key,String def);

	public Integer getIntegerValue(String key);

	public Integer getIntegerValue(String key,Integer def);

	public Long getLongValue(String key);

	public Long getLongValue(String key,Long def);

	public Double getDoubleValue(String key);

	public Double getDoubleValue(String key,Double def);

	public Date getDateValue(String key);

	public Date getDateValue(String key,Date date);

	//因为会有无值的问题,暂时不体提供此方法,请使用getBooleanValue(String key,Boolean def)方法
	//public Boolean getBooleanValue(String key);

	public Boolean getBooleanValue(String key,Boolean def);

	public BigDecimal getBigDecimalValue(String key);

	public BigDecimal getBigDecimalValue(String key,BigDecimal def);

	public void set(String key,Object value);

}
