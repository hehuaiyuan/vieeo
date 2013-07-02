package com.vieeo.module.repository;

import java.io.Serializable;
import java.util.List;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;

public interface GenericRepository<E extends Entity,T extends Serializable> {

	public E get(T id);

	public E get(Conditions conditions);

	public T save(E entity);

	public void update(E entity);

	public T saveOrUpdate(E entity);

	public  void deleteByIds(T ... ids);

	public  void deleteByIdsWithForeign(T ... ids);

	public  void deleteAll();

	public  void delete(E entity);

	public  void delete(Conditions conditions);

	public  List<E> findAll();

	public  List<E> find(String hql,Object... values);

	public  List<E> find(Conditions conditions);

	public  Pagination<E> find(int pageNum,int pageSize);

	public  Pagination<E> find(int pageNum,int pageSize,String hql,Object... values);

	public  Pagination<E> find(Conditions conditions,int pageNum,int pageSize);

	public void execute(String sql,Object... values);

}
