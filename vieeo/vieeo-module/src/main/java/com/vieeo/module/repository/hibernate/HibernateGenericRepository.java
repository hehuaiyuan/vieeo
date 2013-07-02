package com.vieeo.module.repository.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;
import com.vieeo.module.dao.EntityDaoException;
import com.vieeo.module.dao.GenericDao;
import com.vieeo.module.repository.GenericRepository;
import com.vieeo.module.repository.RepositoryException;
import com.vieeo.util.ListUtils;


public class HibernateGenericRepository<E extends Entity,T extends Serializable> implements GenericRepository<E,T> {

	Logger log = Logger.getLogger(HibernateGenericRepository.class);

	private GenericDao entityDao;

	private Class<E> clazz;

	//为方便cglib生成代理对象而放置一个默认构造函数,太阴险了，spring用cglib生成代理后居然把注册好的bean传到代理对象中
	//代理类在覆盖方法里又调回来了，这样就可以解释通为什么没注入clazz对象也能用 .....
	public HibernateGenericRepository(){

	}

	public HibernateGenericRepository(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public E get(T id) {
		E result = null;
		try {
			result = entityDao.get(clazz, id);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("get error:"+e.getMessage());
		}
		return result;
	}

	@Override
	public E get(Conditions conditions) {
		E result = null;
		try {
			result = entityDao.get(clazz, conditions);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error(" getByConditions error:"+e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(E entity) {
		try {
			return (T)entityDao.save(entity);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			throw new RepositoryException(clazz.getName()+" save error"+e.getMessage(),e);
		}
	}

	@Override
	public void update(E entity) {
		try {
			entityDao.update(entity);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			throw new RepositoryException(clazz.getName()+" update error:"+e.getMessage(),e);
		}
	}

	public T saveOrUpdate(E entity){
		if(entity == null) throw new RepositoryException(clazz.getName()+" saveOrUpdate error: entity is null");
		if(entity.getId() == null || isBlank(entity.getId())) return save(entity);
		else {
			update(entity);
			return null;
		}
	}

	private boolean isBlank(Object id) {
		if(String.class.isInstance(id)) {
			return StringUtils.isBlank((String)id);
		}
		return false;
	}

	@Override
	public void deleteByIds(T... ids) {
		try {
			if(ids == null || ids.length<=0) return ;
			entityDao.deleteById(clazz, ids);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			throw new RepositoryException(clazz.getName()+" deleteByIds error:"+e.getMessage(),e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			entityDao.deleteAll(clazz);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			throw new RepositoryException(clazz.getName()+" deleteAll error:"+e.getMessage(),e);
		}
	}

	@Override
	public void delete(Conditions conditions) {
		try {
			entityDao.delete(clazz, conditions);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			throw new RepositoryException(clazz.getName()+" deleteByConditions error:"+e.getMessage(),e);
		}
	}

	@Override
	public List<E> findAll() {
		List<E> result = null;
		try {
			result = entityDao.find(clazz);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findAll error:"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<E> find(String hql, Object... values) {
		List<E> result = null;
		try {
			result = entityDao.find(clazz, hql, values);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findByHql error:"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<E> find(Conditions conditions) {
		List<E> result = null;
		try {
			result = entityDao.find(clazz, conditions);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findByConditions error:"+e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<E> find(int pageNum, int pageSize) {
		Pagination<E> result = null;
		try {
			result = entityDao.find(clazz, pageNum, pageSize);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findByPage error:"+e.getMessage());
		}
		return result == null ? (Pagination<E>)Pagination.getDefaultInstance() : result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<E> find(int pageNum, int pageSize, String hql,
			Object... values) {
		Pagination<E> result = null;
		try {
			result = entityDao.find(clazz, pageNum, pageSize, hql, values);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findByHqlPage error:"+e.getMessage());
		}
		return result == null ? (Pagination<E>)Pagination.getDefaultInstance() : result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<E> find(Conditions conditions, int pageNum, int pageSize) {
		Pagination<E> result = null;
		try {
			result = entityDao.find(clazz, conditions, pageNum, pageSize);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("findByConditionsPage error:"+e.getMessage());
		}
		return result == null ? (Pagination<E>)Pagination.getDefaultInstance() : result;
	}

	@Override
	public void execute(String sql, Object... values) {
		try {
			entityDao.execute(sql, values);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("executeSql error:"+e.getMessage());
			throw new RepositoryException(clazz.getName()+" executeSql error:"+e.getMessage(),e);
		}
	}

	public GenericDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(GenericDao entityDao) {
		this.entityDao = entityDao;
	}

	@Override
	public void delete(E entity) {
		try {
			entityDao.delete(entity);
		} catch (EntityDaoException e) {
			e.printStackTrace();
			log.error("executeSql error:"+e.getMessage());
			throw new RepositoryException(clazz.getName()+" delete entity error:"+e.getMessage(),e);
		}
	}

	@Override
	public void deleteByIdsWithForeign(T... ids) {
		if(ids == null || ids.length<=0) return ;
		List<E> result = find(Conditions.create().and(Expressions.in("id", ids)));
		if(ListUtils.isEmpty(result)) return ;
		for (E entity : result) {
			delete(entity);
		}
	}

}
