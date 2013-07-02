package com.vieeo.orm.jdbc.spi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vieeo.orm.jdbc.query.QueryCallback;
import com.vieeo.orm.jdbc.support.CollectorException;

/**
 * jdbc操作接口
 * @author hehy
 */
public interface JdbcCollector extends Collector,Serializable {

	public <R> R query(String sql, Object[] params, QueryCallback<R> callback) throws CollectorException;

	public List<Map<String, Object>> query(String sql, Object[] params) throws CollectorException;

}
