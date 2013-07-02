package com.vieeo.orm.jdbc.query;

import java.sql.ResultSet;

import com.vieeo.orm.jdbc.JdbcException;

public interface QueryCallback<R> {
	public R doInReslutSet(ResultSet rs) throws JdbcException;
}
