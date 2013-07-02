package com.vieeo.module.dao.hibernate3;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vieeo.core.build.Builder;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expression;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.condition.expression.DeleteExpression;
import com.vieeo.core.condition.expression.PageSelectExpression;
import com.vieeo.core.condition.expression.PageSelectWithoutClazzExpression;
import com.vieeo.core.condition.expression.SelectExpression;
import com.vieeo.core.domain.BaseEntity;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;
import com.vieeo.core.strategy.Visitor;
import com.vieeo.module.context.ConditionsContext;
import com.vieeo.module.dao.EntityDaoException;
import com.vieeo.module.dao.GenericDao;
import com.vieeo.module.dao.hibernate3.visitor.HQLBuilder;
import com.vieeo.module.dao.hibernate3.visitor.HibernateVisitor;

public class HibernateDao extends HibernateDaoSupport implements GenericDao{

	/**
	 * HQL构造器
	 */
	private Builder<String,ConditionsContext> hqlBuilder;

	@Override
	public <E extends Entity,T extends Serializable> void deleteById(Class<E> clazz,T ... ids)throws EntityDaoException {
		try {
			final String hql = getHql(clazz,Conditions.create().and(Expressions.in("id", (Object[])ids)),new DeleteExpression(clazz));
			executeHql(hql,ids);
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}

	}

	@Override
	public <E extends Entity> void deleteAll(Class<E> clazz)throws EntityDaoException {
		delete(clazz,null);
	}

	@Override
	public <E extends Entity> void delete(Class<E> clazz,
			Conditions conditions)throws EntityDaoException {
		String hql =null;
		try {
			hql = getHql(clazz,conditions,new DeleteExpression(clazz));
			executeHql(hql,conditions != null && conditions.getValues() != null ? conditions.getValues().toArray() : null);
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}


	@Override
	public <E extends Entity> List<E> find(Class<E> clazz) throws EntityDaoException {
		return find(clazz,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> List<E> find(Class<E> clazz,
			Conditions conditions) throws EntityDaoException{
		try {
			return (List<E>)getHibernateTemplate().find(new HibernateVisitor(new SelectExpression(clazz),
					clazz,conditions != null?conditions.getAlias():null).visit(conditions),
					conditions != null && conditions.getValues() != null ? conditions.getValues().toArray() : null);
		} catch (DataAccessException e) {
			throw new EntityDaoException(e.getMessage(),e);
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@Override
	public <E extends Entity> Pagination<E> find(Class<E> clazz,
			int pageNum, int pageSize) throws EntityDaoException{
		return find(clazz,null,pageNum,pageSize);
	}

	@Override
	public <E extends Entity> Pagination<E> find(Class<E> clazz,
			Conditions conditions,int pageNum,int pageSize)throws EntityDaoException {
		try {
			final Object[] params = conditions != null && conditions.getValues() != null ? conditions.getValues().toArray() : null;
			final String hql = getHql(clazz,conditions,new SelectExpression(clazz));
			return createPage(clazz,hql,conditions,params,pageNum,pageSize,new PageSelectExpression(clazz));
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@Override
	public <E extends Entity, T extends Serializable> E get(Class<E> clazz,
			T id)throws EntityDaoException {
		return (E)getHibernateTemplate().get(clazz, id);
	}

	@Override
	public <E extends Entity, T extends Serializable> E get(Class<E> clazz,
			Conditions conditions)throws EntityDaoException {
		Visitor<Conditions> visitor = new HibernateVisitor(new SelectExpression(clazz),clazz,conditions != null ? conditions.getAlias() : null);
		try {
			String hql = visitor.visit(conditions);
			//不能直接用条件查询,如果记录多的话性能太差,改用分页查询来限制
			//List<E> result = this.getHibernateTemplate().find(hql, conditions.getValues().toArray());
			List<E> result = findByLimit(hql, conditions.getValues().toArray(), 0, 1, null);
			return result != null && result.size()> 0 ? result.get(0) : null;
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity,T extends Serializable> T save(E entity) throws EntityDaoException{
		return (T)getHibernateTemplate().save(entity);
	}

	@Override
	public <E extends Entity> void update(E entity)throws EntityDaoException {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> List<E> find(Class<E> clazz, String hql,
			Object... values) {
		return (List<E>)getHibernateTemplate().find(hql, values);
	}

	@Override
	public <E extends Entity> Pagination<E> find(Class<E> clazz,
			int pageNum, int pageSize, String hql, Object... values)throws EntityDaoException {
		try {
			return createPage(clazz,hql,null,values,pageNum,pageSize,new PageSelectWithoutClazzExpression(hql));
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@Override
	public void execute(String sql, Object... values) throws EntityDaoException {
		executeHql(sql,values);
	}

	@SuppressWarnings("unchecked")
	private <E> List<E> findByLimit(final String hql,final Object[] params,final int pageNum,final int pageSize,final ResultTransformer transformer){
		return (List<E>)getHibernateTemplate().execute(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				query.setFirstResult(pageNum);
				query.setMaxResults(pageSize);
				if(transformer != null) query.setResultTransformer(transformer);
				return query.list();
			}
		});
	}

	private <E extends Entity> Pagination<E> createPage(Class<E> clazz,String hql,Conditions conditions,Object[] params,int pageNum,int pageSize,Expression expression) throws Exception{
		Pagination<E> result = new Pagination<E>();
		//hql = conditions == null ? hql : getHql(clazz, conditions, expression);
		int totalCount = this.count(getHql(clazz, conditions, expression), params);
		final int firstResult = skip(result, totalCount, pageNum, pageSize);
		final int maxResults = result.getPageSize();

		List<E> data = findByLimit(hql,params,firstResult,maxResults,null);
		result.setData(data);
		return result;
	}

	@SuppressWarnings("unchecked")
	protected int count(String hql,Object[] params) {
		List<Long> result = getHibernateTemplate().find(hql, params);
		return result.isEmpty() ? 0 : result.get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	private int countBySql(final String sql,final Object[] params) {
		Long count = getHibernateTemplate().execute(new HibernateCallback<Long>(){
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				List<BigInteger> result = query.list();
				return result.isEmpty() ? 0 : result.get(0).longValue();
			}
		});
		return count == null ? 0 : count.intValue();
		//return result.isEmpty() ? 0 : result.get(0).intValue();
	}

	private void executeHql(final String hql,final Object[] params) {
		getHibernateTemplate().execute(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				return query.executeUpdate();
			}
		});
	}

	protected <E extends Entity> String getHql(Class<E> clazz ,Conditions conditions,Expression expression) throws Exception{
		if(hqlBuilder == null) hqlBuilder = new HQLBuilder();
		return hqlBuilder.build(ConditionsContext.create(clazz, conditions, expression));
		 //new HibernateVisitor(expression,clazz,conditions != null ? conditions.getAlias():null).visit(conditions);
	}

	protected <E extends Entity> int skip(Pagination<E> page, int rows, int num, int size) {
		num  = num > 0  ? num  :  1;
		size = size > 0 ? size : Pagination.DEFAULT_PAGE_SIZE;

		page.setPageNum(num - 1);
		page.setTotalCount(rows);
		page.setPageSize(size);
		page.calc();
		return size * page.getPageNum();
	}

	@Override
	public <E extends Entity> void delete(E entity)
			throws EntityDaoException {
		getHibernateTemplate().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> List<E> find(final String hql,final Object... params)
			throws EntityDaoException {
		return (List<E>)getHibernateTemplate().execute(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
	}

	@Override
	public <E extends Entity> Pagination<E> find(int pageNum,
			int pageSize, String hql, Object... values)
			throws EntityDaoException {
		try {
			Pagination<E> result = new Pagination<E>();
			int totalCount = this.count(getHql(BaseEntity.class,null,new PageSelectWithoutClazzExpression(hql)), values);

			final int firstResult = skip(result, totalCount, pageNum, pageSize);
			final int maxResults = result.getPageSize();

			List<E> data = findByLimit(hql,values,firstResult,maxResults,Criteria.ALIAS_TO_ENTITY_MAP);
			result.setData(data);
			return result;
		}catch(Exception e){
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> List<E> executeFind(final String sql, final Object... params)
			throws EntityDaoException {
		return (List<E>)getHibernateTemplate().executeFind(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
	}

	@Override
	public <E extends Entity> Pagination<E> executeFind(int pageNum,int pageSize,String sql,
			Object... params) throws EntityDaoException {
		try {
			Pagination<E> result = new Pagination<E>();
			int totalCount = this.countBySql(getHql(BaseEntity.class,null,new PageSelectWithoutClazzExpression(sql)), params);
			final int firstResult = skip(result, totalCount, pageNum, pageSize);
			final int maxResults = result.getPageSize();

			List<E> data = findBySqlLimit(firstResult,maxResults,sql,params);
			result.setData(data);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityDaoException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	private <E> List<E> findBySqlLimit(final int pageNum,final int pageSize,final String sql,
			final Object... params) {
		return (List<E>)getHibernateTemplate().execute(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i , params[i]);
					}
				}
				query.setFirstResult(pageNum);
				query.setMaxResults(pageSize);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				//if(getSqlResultTransformer() != null) query.setResultTransformer(getSqlResultTransformer());
				return query.list();
			}
		});
	}

	public Builder<String, ConditionsContext> getHqlBuilder() {
		return hqlBuilder;
	}

	public void setHqlBuilder(Builder<String, ConditionsContext> hqlBuilder) {
		this.hqlBuilder = hqlBuilder;
	}

}
