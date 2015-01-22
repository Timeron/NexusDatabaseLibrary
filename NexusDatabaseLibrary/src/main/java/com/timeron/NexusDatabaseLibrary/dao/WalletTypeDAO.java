package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateJdbcException;

import com.timeron.NexusDatabaseLibrary.Entity.WalletType;

public class WalletTypeDAO extends DaoImp<WalletType>{

	public WalletTypeDAO(Class<WalletType> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<WalletType> getAll() throws HibernateJdbcException {
		List<WalletType> result = new ArrayList<WalletType>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletType.class);
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("date"));
		result = criteria.list();
		
		session.close();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletType> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WalletType> getAllParents() {
		List<WalletType> result = new ArrayList<WalletType>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletType.class);
		criteria.add(Restrictions.isNull("parentType"));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("name"));
		result = criteria.list();
		
		session.close();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletType> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
	
	public boolean checkIfAvailableByName(String name){
		boolean result = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletType.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		if(criteria.list().size() > 0){
			result = true;
		}
		session.close();
		return result;
	}
	
	public WalletType getByName(String name){
		WalletType result = new WalletType();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletType.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		result = (WalletType) criteria.list().get(0);
		session.close();
		return result;
	}
	
	
}
