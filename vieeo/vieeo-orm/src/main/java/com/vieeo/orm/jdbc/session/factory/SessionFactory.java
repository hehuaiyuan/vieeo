package com.vieeo.orm.jdbc.session.factory;

import com.vieeo.orm.jdbc.session.Session;
import com.vieeo.orm.jdbc.spi.Collector;

public interface SessionFactory<C extends Collector> {

	public void openSessionFactory();

	public Session<C> openSession();

	public void closeSession(Session<C> session);

}
