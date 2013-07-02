package com.vieeo.module.service.support;

import java.io.Serializable;
import java.util.List;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;
import com.vieeo.module.repository.GenericRepository;
import com.vieeo.module.service.GenericService;

public abstract class RepositoryService<E extends Entity,T extends Serializable> implements GenericService<E,T>{

	private GenericRepository<E,T> repository;

	@Override
	public E get(T id) {
		return repository.get(id);
	}

	@Override
	public E get(Conditions conditions) {
		return repository.get(conditions);
	}

	@Override
	public T save(E entity) {
		return repository.save(entity);
	}

	@Override
	public void update(E entity) {
		repository.update(entity);
	}

	@Override
	public T saveOrUpdate(E entity) {
		return repository.saveOrUpdate(entity);
	}

	@Override
	public void deleteByIds(T... ids) {
		repository.deleteByIds(ids);
	}

	@Override
	public void deleteByIdsWithForeign(T... ids) {
		repository.deleteByIdsWithForeign(ids);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void delete(E entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Conditions conditions) {
		repository.delete(conditions);
	}

	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public List<E> find(String hql, Object... values) {
		return repository.find(hql,values);
	}

	@Override
	public List<E> find(Conditions conditions) {
		return repository.find(conditions);
	}

	@Override
	public Pagination<E> find(int pageNum, int pageSize) {
		return repository.find(pageNum,pageSize);
	}

	@Override
	public Pagination<E> find(int pageNum, int pageSize, String hql,
			Object... values) {
		return repository.find(pageNum,pageSize,hql,values);
	}

	@Override
	public Pagination<E> find(Conditions conditions, int pageNum,
			int pageSize) {
		return repository.find(conditions,pageNum,pageSize);
	}

	@Override
	public void execute(String sql, Object... values) {
		repository.execute(sql,values);
	}

	public GenericRepository<E, T> getRepository() {
		return repository;
	}

	public void setRepository(GenericRepository<E, T> repository) {
		this.repository = repository;
	}

}
