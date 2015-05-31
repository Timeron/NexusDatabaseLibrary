package com.timeron.NexusDatabaseLibrary.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.WalletAccount;

@Repository
public class WalletDAO extends DaoImp<WalletAccount>{
	
	public WalletDAO() {
		super(WalletAccount.class);
	}

	static Logger log = Logger.getLogger(WalletDAO.class.getName());
	
}
