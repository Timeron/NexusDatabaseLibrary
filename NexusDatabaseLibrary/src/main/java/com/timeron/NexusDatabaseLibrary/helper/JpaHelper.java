package com.timeron.NexusDatabaseLibrary.helper;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class JpaHelper {

	public static Session createSession(EntityManager entityManager, Class<?> persistantClass){
		Session session = getSession(entityManager);
		
		if(session == null){
			throw new RuntimeException("does not support session");
		}
		
		return session;
	}
	
	public static Criteria createCriteria(EntityManager entityManager, Class<?> persistantClass){
		Session session = getSession(entityManager);
		
		if(session == null){
			throw new RuntimeException("does not support criteria");
		}
		
		return session.createCriteria(persistantClass);
	}

	private static Session getSession(EntityManager entityManager) {
		Session session = null;
		Object delegate = entityManager.getDelegate();
		
		if(delegate instanceof Session){
			session = (Session) delegate;
		}else{
			try{
				Method method = delegate.getClass().getMethod("getSession", (Class<?>[]) null);
				
				if(method.getReturnType() == Session.class){
					session = (Session) method.invoke(delegate, (Object[]) null);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return session;
	}
	
}
