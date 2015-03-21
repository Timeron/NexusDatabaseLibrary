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
import com.timeron.NexusDatabaseLibrary.dao.Enum.Direction;

@Repository
public class WalletRecordDAO extends DaoImp<WalletRecord>{

	public WalletRecordDAO() {
		super(WalletRecord.class);
	}
	
	public WalletRecordDAO(Class<WalletRecord> persistantClass) {
		super(persistantClass);
	}

	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount) {
		int rows = 0;
		return getRecordsFromAccount(currentAccount, null ,rows);
	}
	
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, int rows) {
		return getRecordsFromAccount(currentAccount, null ,rows);
	}
	
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, String direction) {
		int rows = 0;
		return getRecordsFromAccount(currentAccount, direction ,rows);
	}
	
	@SuppressWarnings("unchecked")
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, String direction, int rows) {
		List<WalletRecord> result = new ArrayList<WalletRecord>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		criteria = session.createCriteria(WalletRecord.class)
				.add(Restrictions.disjunction()
						.add(Restrictions.eq("walletAccount", currentAccount))
						.add(Restrictions.eq("destinationWalletAccount", currentAccount)));
		if(direction != null){
			if(Direction.DESC == direction){
				criteria.addOrder(Order.desc("date"));
			}else{
				criteria.addOrder(Order.asc("date"));
			}
		}
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
