package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.SessionFactory;

public interface DAO<T> {

	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	
}
