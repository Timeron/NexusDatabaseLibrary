package com.timeron.NexusDatabaseLibrary.dao;

import com.timeron.NexusDatabaseLibrary.Entity.JStatus;

public class JStatusDAO extends DaoImp<JStatus>  {

	public JStatusDAO(Class<JStatus> persistantClass) {
		super(persistantClass);
	}
	
	public JStatusDAO(){
		super(JStatus.class);
	}

}
