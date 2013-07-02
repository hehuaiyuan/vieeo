package com.vieeo.core.domain;

import java.io.Serializable;
import java.util.Date;

public interface Entity extends Serializable,Cloneable{
	public enum EntityConext{
		ENTITY_DATACREATED("dateCreated"),ENTITY_DATALASTMODIFED("dateLastModified"),
		ENTITY_USERCREATED("userCreated"),ENTITY_USERLASTMODIFED("userLastModified");

		private String name;

		private EntityConext(String name) {
			this.name = name;
		}

		public String getName(){
			return this.name;
		}
	}

	public <PK extends Serializable> PK getId();

	public void setDateCreated(Date dateCreated);

	public void setDateLastModified(Date dateLastModified);

	public void setUserCreated(String userCreated);

	public void setUserLastModified(String userLastModifed);

}
