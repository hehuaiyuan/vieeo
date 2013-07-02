package com.vieeo.module.dao;

import java.io.Serializable;
import java.util.List;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;

public interface GenericDao {

	public <E extends Entity,T extends Serializable> E get(Class<E> clazz,T id) throws EntityDaoException;

	public <E extends Entity,T extends Serializable> E get(Class<E> clazz,Conditions conditions)throws EntityDaoException;

	public <E extends Entity,T extends Serializable> T save(E entity)throws EntityDaoException;

	public <E extends Entity> void update(E entity)throws EntityDaoException;

	public <E extends Entity,T extends Serializable> void deleteById(Class<E> clazz,T ... ids)throws EntityDaoException;

	public <E extends Entity> void deleteAll(Class<E> clazz)throws EntityDaoException;

	public <E extends Entity> void delete(Class<E> clazz,Conditions conditions)throws EntityDaoException;

	public <E extends Entity> void delete(E entity)throws EntityDaoException;

	public <E extends Entity> List<E> find(Class<E> clazz)throws EntityDaoException;

	public <E extends Entity> List<E> find(Class<E> clazz,String hql,Object... values)throws EntityDaoException;

	public <E extends Entity> List<E> find(String hql,Object... values)throws EntityDaoException;

	public <E extends Entity> List<E> find(Class<E> clazz,Conditions conditions)throws EntityDaoException;

	public <E extends Entity> Pagination<E> find(Class<E> clazz,int pageNum,int pageSize)throws EntityDaoException;

	public <E extends Entity> Pagination<E> find(Class<E> clazz,int pageNum,int pageSize,String hql,Object... values)throws EntityDaoException;

	public <E extends Entity> Pagination<E> find(Class<E> clazz,Conditions conditions,int pageNum,int pageSize)throws EntityDaoException;

	public <E extends Entity> Pagination<E> find(int pageNum,int pageSize,String hql,Object... values)throws EntityDaoException;

	public void execute(String sql,Object... values)throws EntityDaoException;

	public <E extends Entity> List<E> executeFind(String sql,Object... values)throws EntityDaoException;

	public <E extends Entity> Pagination<E> executeFind(int pageNum,int pageSize,String sql,Object... values)throws EntityDaoException;

}
