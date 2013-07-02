package com.vieeo.module.hibernate;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * hibernate服务类
 * @author roy.he
 *
 */
public class HibernateService {
	
	private SessionFactory sessionFactory;
	
	/**
	 * 获取当前线程的session
	 * @return
	 */
	public Session getCurrSession(){
		return sessionFactory == null ? null : sessionFactory.getCurrentSession();
	}
	
	/**
	 * 清空当前线程的缓存
	 */
	public void flushCurrSession(){
		Session session = getCurrSession();
		if(session != null){
			session.flush();
		}
	}
	
	/**
	 * 获取一个新线程
	 * @return
	 */
	public Session getNewSession(){
		return sessionFactory == null ? null : sessionFactory.openSession();
	}
	
	/**
	 * 关闭一个线程
	 * @param session
	 */
	public void closeSession(Session session){
		if(session != null && session.isOpen()){
			session.close();
		}
	}
	
	public int executeSQL(String sql,Object... params){
		Session session = getCurrSession();
		if(session == null) throw new RuntimeException("can not execute sql without session:"+sql);
		SQLQuery query = session.createSQLQuery(sql);
		if(params != null && params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
