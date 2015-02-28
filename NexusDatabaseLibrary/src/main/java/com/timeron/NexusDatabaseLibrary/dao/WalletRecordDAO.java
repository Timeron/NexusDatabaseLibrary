package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;
import com.timeron.NexusDatabaseLibrary.Entity.WalletRecord;

@Repository
public class WalletRecordDAO extends DaoImp<WalletRecord>{

	public WalletRecordDAO(Class<WalletRecord> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount) {
		int rows = 0;
		return getRecordsFromAccount(currentAccount, rows);
	}
	
	@SuppressWarnings("unchecked")
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, int rows) {
		List<WalletRecord> result = new ArrayList<WalletRecord>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletRecord.class)
				.add(Restrictions.disjunction()
						.add(Restrictions.eq("walletAccount", currentAccount))
						.add(Restrictions.eq("destinationWalletAccount", currentAccount)));
		criteria.addOrder(Order.asc("date"));
		if(rows != 0){
			criteria.setMaxResults(rows);
		}
		result = criteria.list();
		
		session.close();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletRecord> emptyList = Collections.emptyList();
			return emptyList;
		}
	}


	
}
