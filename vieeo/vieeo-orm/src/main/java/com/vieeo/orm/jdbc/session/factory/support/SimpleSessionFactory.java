package com.vieeo.orm.jdbc.session.factory.support;

import com.vieeo.orm.jdbc.Connector;
import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.session.Session;
import com.vieeo.orm.jdbc.session.factory.SessionFactory;
import com.vieeo.orm.jdbc.spi.Collector;
import com.vieeo.orm.jdbc.transaction.JdbcTransactionSession;

public class SimpleSessionFactory<C extends Collector> implements SessionFactory<C> {

	private final ThreadLocal<Session<C>> local = new ThreadLocal<Session<C>>();

	private Connector connector;

	@Override
	public void closeSession(Session<C> session) {
		try {
			session.destroy();
		} catch (JdbcException e) {
		}
	}

	@Override
	public Session<C> openSession() {
		return getSession(connector);
	}

	@SuppressWarnings("unchecked")
	private Session<C> getSession(Connector connector) {
		if(local.get() == null) {
			Session<C> session = (Session<C>)new JdbcTransactionSession(connector);
			local.set(session);
			return session;
		}else {
			return local.get();
		}
	}

	@Override
	public void openSessionFactory() {

	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

}
