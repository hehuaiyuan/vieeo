package com.vieeo.orm.jdbc;


/**
 * dataSource超类
 * @author hehy
 *
 */
public abstract class PoolAbstractConnector extends AbstractConnector{

	private int minPoolSize;

	private int maxPoolSize;

	private int initialPoolSize;

	public PoolAbstractConnector(){}

	public PoolAbstractConnector(JdbcPacket packet){
		super(packet);
		setMinPoolSize(packet.getMinPoolSize());
		setMaxPoolSize(packet.getMaxPoolSize());
		setInitialPoolSize(packet.getInitialPoolSize());
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

}
