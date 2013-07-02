package com.vieeo.module.dao.hibernate3.transformer;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

public class BizEntityForSqlTransformer implements ResultTransformer{

	private static final long serialVersionUID = 1488391629711675260L;

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List result) {
		return result;
	}

	@Override
	public Object transformTuple(Object[] arg0, String[] arg1) {
		return arg0;
	}

}
