package com.vieeo.module.dao.hibernate3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.vieeo.core.domain.Entity;
import com.vieeo.module.session.UserSession;

public class Hibernate3Interceptor extends EmptyInterceptor{

	private static final long serialVersionUID = -1813912759753790394L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if(Entity.class.isInstance(entity)) {
			Entity obj = (Entity)entity;
			boolean modified = false;
			for (int i=0;i<propertyNames.length;i++) {
				if(Entity.EntityConext.ENTITY_DATALASTMODIFED.getName().equals(propertyNames[i])) {
					//Timestamp dateLastModified = new Timestamp(System.currentTimeMillis());
					Date dateLastModified = Calendar.getInstance().getTime();
					currentState[i] = dateLastModified;
					obj.setDateLastModified(dateLastModified);
					modified = true;
				}else if(Entity.EntityConext.ENTITY_USERLASTMODIFED.getName().equals(propertyNames[i])) {
					String user = UserSession.get();
					currentState[i] = user;
					obj.setUserLastModified(user);
					modified = true;
				}
			}
			return modified;
		}else{
			return super.onFlushDirty(entity, id, currentState, previousState,
					propertyNames, types);
		}
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if(Entity.class.isInstance(entity)) {
			Entity obj = (Entity)entity;
			boolean modified = false;
			for (int i=0;i<propertyNames.length;i++) {
				if(Entity.EntityConext.ENTITY_DATACREATED.getName().equals(propertyNames[i])) {
					//Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
					Date dateCreated = Calendar.getInstance().getTime();
					state[i] = dateCreated;
					obj.setDateCreated(dateCreated);
					modified = true;
				}else if(Entity.EntityConext.ENTITY_USERCREATED.getName().equals(propertyNames[i])) {
					String user = UserSession.get();
					state[i] = user;
					obj.setUserCreated(user);
					modified = true;
				}
			}
			return modified;
		}else{
			return super.onSave(entity, id, state, propertyNames, types);
		}
	}

}
