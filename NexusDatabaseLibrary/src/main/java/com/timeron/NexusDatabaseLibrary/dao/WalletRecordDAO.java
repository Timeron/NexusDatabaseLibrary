package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;
import com.timeron.NexusDatabaseLibrary.Entity.WalletRecord;
import com.timeron.NexusDatabaseLibrary.dao.Enum.Direction;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class WalletRecordDAO extends DaoImp<WalletRecord>{

	public WalletRecordDAO() {
		super(WalletRecord.class);
	}
	@Transactional
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount) {
		int rows = 0;
		String orderBy = "date";
		return getRecordsFromAccount(currentAccount, orderBy, null ,rows);
	}
	@Transactional
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, int rows) {
		String orderBy = "date";
		return getRecordsFromAccount(currentAccount, orderBy, null ,rows);
	}
	@Transactional
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, String direction) {
		int rows = 0;
		String orderBy = "date";
		return getRecordsFromAccount(currentAccount, orderBy, direction ,rows);
	}
	@Transactional
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, String orderBy, String direction) {
		int rows = 0;
		return getRecordsFromAccount(currentAccount, orderBy, direction ,rows);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<WalletRecord> getRecordsFromAccount(WalletAccount currentAccount, String orderBy, String direction, int rows) {
		List<WalletRecord> result = new ArrayList<WalletRecord>();
		Criteria criteria = JpaHelper.createCriteria(entityManager, persistantClass);
		criteria = criteria.add(Restrictions.eq("walletAccount", currentAccount));
		if(direction != null){
			if(Direction.DESC == direction){
				criteria.addOrder(Order.desc(orderBy));
			}else{
				criteria.addOrder(Order.asc(orderBy));
			}
		}
		if(rows != 0){
			criteria.setMaxResults(rows);
		}
		result = criteria.list();
		
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletRecord> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WalletRecord> getRecordsFromAccountWithParentType(
			WalletAccount currentAccount, Boolean income) {
		List<WalletRecord> result = new ArrayList<WalletRecord>();
		Query query = entityManager.createNamedQuery("GetRecordsFromAccountWithParentType");
		query.setParameter("accountId", currentAccount.getId());
		query.setParameter("income", income);
		result = (List<WalletRecord>) query.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletRecord> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
	@SuppressWarnings("unchecked")
	public List<WalletRecord> getRecordsFromAccountWithType(
			WalletAccount currentAccount, boolean income) {
		List<WalletRecord> result = new ArrayList<WalletRecord>();
		Query query = entityManager.createNamedQuery("GetRecordsFromAccountWithAllTypes");
		query.setParameter("accountId", currentAccount.getId());
		query.setParameter("income", income);
		result = (List<WalletRecord>) query.getResultList();
		if (result.size() > 0) {
			return result;
		} else {
			List<WalletRecord> emptyList = Collections.emptyList();
			return emptyList;
		}
	}


	
}
