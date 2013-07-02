package com.vieeo.module.repository;

import java.util.List;

import com.vieeo.core.domain.BizEntity;
import com.vieeo.core.domain.Pagination;

/**
 * 针对BizEntity对象提供的操作接口,暂时只针对查询
 * @author roy.he
 *
 */
public interface GenericBizRepository{
	
	public BizEntity query(String hql,Object... values);
	
	public  List<BizEntity> find(String hql,Object... values);
	
	public  List<BizEntity> findBySql(String sql,Object... values);
	
	public  Pagination<BizEntity> find(int pageNum,int pageSize,String hql,Object... values);
	
	public  Pagination<BizEntity> findBySql(int pageNum,int pageSize,String sql,Object... values);

}
