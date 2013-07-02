package com.vieeo.core.domain;

import java.io.Serializable;
import java.util.Date;

public class AbstractEntity<PK extends Serializable> implements Entity{

	private static final long serialVersionUID = -8755696765346668152L;

	protected PK id;

	protected Date dateCreated;

	protected Date dateLastModified;

	protected String userCreated;

	protected String userLastModified;

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public String getUserLastModified() {
		return userLastModified;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK getId() {
		return id;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	@Override
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	@Override
	public void setUserLastModified(String userLastModifed) {
		this.userLastModified = userLastModifed;
	}

	//为了方便添加修改记录时记录操作人,User对象实现此方法,filter用此方法获得操作人
	public String getOperationUser() {
		return null;
	}
}
