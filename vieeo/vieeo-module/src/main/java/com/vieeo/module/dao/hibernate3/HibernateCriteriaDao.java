package com.vieeo.module.dao.hibernate3;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.vieeo.core.build.Builder;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.expression.PageSelectExpression;
import com.vieeo.core.domain.Entity;
import com.vieeo.core.domain.Pagination;
import com.vieeo.module.context.ConditionsContext;
import com.vieeo.module.dao.EntityDaoException;
import com.vieeo.module.dao.hibernate3.visitor.HibernateCriteriaBuilder;

public class HibernateCriteriaDao extends HibernateDao{
	
	private Builder<DetachedCriteria,ConditionsContext> criteriabuilder;

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> List<E> find(Class<E> clazz, Conditions conditions)
			throws EntityDaoException {
		try {
			return getHibernateTemplate().findByCriteria(createCriteria(clazz,conditions));
		} catch (DataAccessException e) {
			throw new EntityDaoException(e.getMessage(),e);
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity> Pagination<E> find(Class<E> clazz,
			Conditions conditions, int pageNum, int pageSize)
			throws EntityDaoException {
		try {
			Pagination<E> result = new Pagination<E>();
			final Object[] params = conditions != null && conditions.getValues() != null ? conditions.getValues().toArray() : null;
			final String hql = getHql(clazz,conditions,new PageSelectExpression(clazz));
			int totalCount = count(hql, params);
			final int firstResult = skip(result, totalCount, pageNum, pageSize);
			final int maxResults = result.getPageSize();
			List<E> data = getHibernateTemplate().findByCriteria(createCriteria(clazz,conditions), firstResult, maxResults);
			result.setData(data);
			return result;
		}catch(Exception e){
			throw new EntityDaoException(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity, T extends Serializable> E get(Class<E> clazz,
			Conditions conditions) throws EntityDaoException {
		try {
			List<E> result = getHibernateTemplate().findByCriteria(createCriteria(clazz,conditions), 0, 1);
			return result != null && result.size()> 0 ? result.get(0) : null;
		} catch (DataAccessException e) {
			throw new EntityDaoException(e.getMessage(),e);
		} catch (Exception e) {
			throw new EntityDaoException(e.getMessage(),e);
		}
	}
	
	private <E extends Entity> DetachedCriteria createCriteria(Class<E> clazz,Conditions conditions) throws Exception{
		if(criteriabuilder == null) criteriabuilder = new HibernateCriteriaBuilder();
		DetachedCriteria dc = criteriabuilder.build(ConditionsContext.create(clazz, conditions, null));
		return dc;
	}

	public Builder<DetachedCriteria, ConditionsContext> getCriteriabuilder() {
		return criteriabuilder;
	}

	public void setCriteriabuilder(
			Builder<DetachedCriteria, ConditionsContext> criteriabuilder) {
		this.criteriabuilder = criteriabuilder;
	}

}
