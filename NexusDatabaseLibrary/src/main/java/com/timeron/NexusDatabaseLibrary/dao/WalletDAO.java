package com.timeron.NexusDatabaseLibrary.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;

@Repository
public class WalletDAO extends DaoImp<WalletAccount>{
	
	public WalletDAO(Class<WalletAccount> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	static Logger log = Logger.getLogger(WalletDAO.class.getName());
	
}
