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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.Interface.NexusEntity;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Direction;

@Repository
public abstract class DaoImp<T> implements DAO<T> {

	private Class<T> persistantClass;
	private  EntityManager entityManager;
	protected  Session session;
	protected  Transaction transaction;

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

	public boolean save(T entity) {
		boolean result = true;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}finally{
			session.close();
		}
		return result;
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
		T result;
		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(getPersistantClass());
		criteria.add(Restrictions.idEq(id));
		if(criteria.list().size() > 0){
			result = (T) criteria.list().get(0);
			session.delete(result);
			session.getTransaction().commit();
		}
		session.close();
	}

	public List<T> getAll() {
		return getAll("", Direction.NONE, 0);
	}
	
	public List<T> getAll(int maxResults) {
		return getAll("", Direction.NONE, maxResults);
	}
	
	public List<T> getAll(String orderBy, String direction) {
		return getAll(orderBy, direction, 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(String orderBy, String direction, int maxResults){
		List<T> entities = new ArrayList<T>();

		session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(getPersistantClass());
		if(direction != Direction.NONE){
			if(direction == Direction.ASC){
				criteria.addOrder(Order.asc(orderBy));
			}else{
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		if(maxResults > 0){
			criteria.setMaxResults(maxResults);
		}
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		entities = (List<T>) criteria.list();
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
			if(criteria.list().size()>0){
				entity = (T) criteria.list().get(0);
			}else{
				entity = null;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return entity;
	}
	
	public int getLastId(){
		NexusEntity entity = null;
		int lastId = 0;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			criteria = session.createCriteria(getPersistantClass());
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			if(criteria.list() != null && criteria.list().size() > 0){
				entity = (NexusEntity) criteria.list().get(0);
				lastId = entity.getId();
			}else{
				lastId = 0;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return lastId;
	}

}
