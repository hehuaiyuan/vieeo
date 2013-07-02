package com.vieeo.core.domain;

import java.util.Date;

public abstract class BaseEntity implements Entity{

	private static final long serialVersionUID = 1161420870997422603L;

	protected String id;

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

	public void setId(String id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getId() {
		return this.id;
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
