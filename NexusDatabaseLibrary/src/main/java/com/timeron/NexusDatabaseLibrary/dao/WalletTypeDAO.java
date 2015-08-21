package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.WalletType;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class WalletTypeDAO extends DaoImp<WalletType>{

	public WalletTypeDAO() {
		super(WalletType.class);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<WalletType> getAll() {
		List<WalletType> result = new ArrayList<WalletType>();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("timestamp"));
		result = criteria.list();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletType> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<WalletType> getAllParents() {
		List<WalletType> result = new ArrayList<WalletType>();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.isNull("parentType"));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("name"));
		result = criteria.list();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletType> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
	
	@Transactional
	public boolean checkIfAvailableByName(String name){
		boolean result = false;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		if(criteria.list().size() > 0){
			result = true;
		}

		return result;
	}
	
	@Transactional
	public WalletType getByName(String name){
		WalletType result = new WalletType();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		result = (WalletType) criteria.list().get(0);
		return result;
	}
	
	
}
