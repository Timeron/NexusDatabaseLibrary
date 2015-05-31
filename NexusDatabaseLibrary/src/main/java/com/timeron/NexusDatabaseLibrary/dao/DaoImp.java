package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.Interface.NexusEntity;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Direction;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public abstract class DaoImp<T> implements DAO<T> {

	static Logger LOG = Logger.getLogger(DaoImp.class);
	
	protected Class<T> persistantClass;
	protected  EntityManager entityManager;
//	protected  Session session;
//	protected  Transaction transaction;
//	private static ServiceRegistry serviceRegistry;
//	protected SessionFactory sessionFactory;

	public List<T> results;

	public DaoImp(Class<T> persistantClass) {
		this.persistantClass = persistantClass;
	}
	
	@PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	public Class<T> getPersistantClass() {
		return persistantClass;
	}

	public void setPersistantClass(Class<T> persistantClass) {
		this.persistantClass = persistantClass;
	}

	@Transactional
	public boolean save(T entity) {
		LOG.info("enter save");
		boolean result = true;
		try{
			entityManager.merge(entity);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}finally{

		}
		LOG.info("exit save");
		return result;
	}

	@Transactional
	public void update(T entity) {
		LOG.info("enter update");
		Session session = JpaHelper.createSession(entityManager, persistantClass);

		session.update(entity);
		session.getTransaction().commit();

		LOG.info("exit update");
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void removeById(int id) {
		LOG.info("enter removeById");
		T result;

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.idEq(id));
		if(criteria.list().size() > 0){
			Session session = JpaHelper.createSession(entityManager, persistantClass);
			result = (T) criteria.list().get(0);
			session.delete(result);
			session.getTransaction().commit();
		}

		LOG.info("exit removeById");
	}

	@Transactional
	public List<T> getAll() {
		return getAll("", Direction.NONE, 0);
	}
	
	@Transactional
	public List<T> getAll(int maxResults) {
		return getAll("", Direction.NONE, maxResults);
	}
	
	@Transactional
	public List<T> getAll(String orderBy, String direction) {
		return getAll(orderBy, direction, 0);
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<T> getAll(String orderBy, String direction, int maxResults){
		LOG.info("enter getAll");
		List<T> entities = new ArrayList<T>();

		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
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
		

		LOG.info("exit getAll");
		if (entities.size() > 0) {
			return entities;
		} else {
			List<T> emptyList = Collections.emptyList();
			return emptyList;
		}
		
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T getById(int id) {
		LOG.info("enter getById");
		T entity = null;
		try {
			Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
			
			criteria.add(Restrictions.idEq(id));
			if(criteria.list().size()>0){
				entity = (T) criteria.list().get(0);
			}else{
				entity = null;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {

		}
		LOG.info("exit getById");
		return entity;
	}
	
	@Transactional
	public int getLastId(){
		LOG.info("enter getLastId");
		NexusEntity entity = null;
		int lastId = 0;
		try {
			Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(1);
			if(criteria.list().size() > 0){
				entity = (NexusEntity) criteria.list().get(0);
				lastId = entity.getId();
			}else{
				lastId = 0;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}finally {

		}
		LOG.info("exit getLastId");
		return lastId;
	}

}
