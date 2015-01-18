package com.timeron.NexusDatabaseLibrary.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;

@Repository
public class WalletAccountDAO extends DaoImp<WalletAccount>{

	public WalletAccountDAO(Class<WalletAccount> persistantClass) {
		super(persistantClass);
	}
	
	public WalletAccount getByName(String name){
		WalletAccount result = new WalletAccount();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletAccount.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		result = (WalletAccount) criteria.list().get(0);
		session.close();
		return result;
	}
	
	public boolean checkIfAvailableByName(String name){
		boolean result = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletAccount.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
		if(criteria.list().size() > 0){
			result = true;
		}
		session.close();
		return result;
	}
	
}
