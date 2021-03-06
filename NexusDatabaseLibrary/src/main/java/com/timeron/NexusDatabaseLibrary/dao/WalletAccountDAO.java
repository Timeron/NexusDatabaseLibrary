package com.timeron.NexusDatabaseLibrary.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.NexusPerson;
import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class WalletAccountDAO extends DaoImp<WalletAccount>{

	public WalletAccountDAO() {
		super(WalletAccount.class);
	}
	
	public WalletAccountDAO(Class<WalletAccount> persistantClass) {
		super(persistantClass);
	}
	
	@Transactional
	@SuppressWarnings("static-access")
	public WalletAccount getByName(String name){
		WalletAccount result = new WalletAccount();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		
		if(criteria.list() == null || criteria.list().isEmpty()){
			return null;
		}else{
			result = (WalletAccount) criteria.list().get(0);
			return result;
		}
	}
	
	@Transactional
	@SuppressWarnings("static-access")
	public boolean checkIfAvailableByName(String name){
		boolean result = false;
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		if(criteria.list().size() > 0){
			result = true;
		}
		return result;
	}

	@Transactional
	public boolean checkIfNameIsAvailable(String name, NexusPerson nexusPerson) {
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("owner", nexusPerson));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		if(criteria.list() == null || criteria.list().isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<WalletAccount> getByUser(NexusPerson nexusPerson) {
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria.add(Restrictions.eq("owner", nexusPerson));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<WalletAccount> result = (List<WalletAccount>) criteria.list();
		if(result == null || result.isEmpty()){
			return null;
		}else{
			return result;
		}
	}
	
}
