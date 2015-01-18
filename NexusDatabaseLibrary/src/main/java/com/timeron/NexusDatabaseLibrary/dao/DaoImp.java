package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class DaoImp<T> implements DAO<T> {

	private Class<T> persistantClass;
	private EntityManager entityManager;
	protected static Session session;
	protected static Transaction transaction;

	public Criteria criteria;
	public List<T> results;

	public SessionFactory sessionFactory;

	public DaoImp(Class<T> persistantClass) {
		this.persistantClass = persistantClass;
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getPersistantClass() {
		return persistantClass;
	}

	@Transactional(readOnly = true)
	public T findById(int id) {
		T entity = (T) getEntityManager().find(getPersistantClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getEntityManager().createQuery(
				"select x from " + getPersistantClass().getSimpleName() + " x")
				.getResultList();
	}

	// public T save(T entity){
	// getEntityManager().persist(entity);
	// return entity;
	// }

	// public T update(T entity) {
	// T mergedEntity = getEntityManager().merge(entity);
	// return mergedEntity;
	// }

	public void flush() {
		getEntityManager().flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPersistantClass(Class<T> persistantClass) {
		this.persistantClass = persistantClass;
	}

	public void save(T entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	public void update(T entity) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void removeById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(getPersistantClass());
		criteria.add(Restrictions.idEq(id));
		T result = (T) criteria.list().get(0);
		session.delete(result);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> entities = new ArrayList<T>();

		session = sessionFactory.openSession();
		session.beginTransaction();
		entities = session.createCriteria(getPersistantClass()).list();
		session.close();

		if (entities.size() > 0) {
			return entities;
		} else {
			List<T> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	@SuppressWarnings("unchecked")
	public T getById(int id) {
		T entity = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			criteria = session.createCriteria(getPersistantClass());
			criteria.add(Restrictions.idEq(id));
			entity = (T) criteria.list().get(0);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return entity;
	}

}
