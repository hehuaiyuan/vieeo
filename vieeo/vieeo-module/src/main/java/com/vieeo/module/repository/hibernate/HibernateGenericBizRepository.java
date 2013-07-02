package com.vieeo.module.repository.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vieeo.core.domain.BizEntity;
import com.vieeo.core.domain.Pagination;
import com.vieeo.core.domain.support.BizMapEntity;
import com.vieeo.module.dao.EntityDaoException;
import com.vieeo.module.dao.GenericDao;
import com.vieeo.module.repository.GenericBizRepository;
import com.vieeo.util.ListUtils;

public class HibernateGenericBizRepository implements GenericBizRepository{
	
	Logger log = Logger.getLogger(HibernateGenericBizRepository.class);
	
	private GenericDao entityDao;

	@Override
	public List<BizEntity> find(String hql, Object... values) {
		try {
			List<BizEntity> result = entityDao.find(hql, values);
			if(ListUtils.isEmpty(result)) return null;
			return createBizEntityList(result);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("BizFind error:"+e.getMessage());
		}
		return null;
	}

	@Override
	public Pagination<BizEntity> find(int pageNum, int pageSize, String hql,
			Object... values) {
		try {
			Pagination<BizEntity> result = entityDao.find(pageNum, pageSize, hql, values);
			if(result == null || ListUtils.isEmpty(result.getData())) result = Pagination.getDefaultBizInstance();
			result.setData(createBizEntityList(result.getData()));
			return result;
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("BizFindPage error:"+e.getMessage());
		}
		return Pagination.getDefaultBizInstance();
	}
	
	private List<BizEntity> createBizEntityList(List<BizEntity> data) {
		if(ListUtils.isEmpty(data)) return null;
		List<BizEntity> result = new ArrayList<BizEntity>();
		for (Map<String,Object> entity : data) {
			BizEntity bizEntity = new BizMapEntity();
			bizEntity.putAll(entity);
			result.add(bizEntity);
		}
		return result;
	}

	public GenericDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(GenericDao entityDao) {
		this.entityDao = entityDao;
	}

	@Override
	public List<BizEntity> findBySql(String sql, Object... values) {
		try {
			return entityDao.executeFind(sql, values);
		} catch (EntityDaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pagination<BizEntity> findBySql(int pageNum, int pageSize,
			String sql, Object... values) {
		try {
			Pagination<BizEntity> result = entityDao.executeFind(pageNum, pageSize, sql, values);
			if(result == null || ListUtils.isEmpty(result.getData())) return null;
			result.setData(createBizEntityList(result.getData()));
			return result;
		} catch (EntityDaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BizEntity query(String hql, Object... values) {
		List<BizEntity> result = find(hql,values);
		return ListUtils.isEmpty(result) ? null : result.get(0);
	}

}
