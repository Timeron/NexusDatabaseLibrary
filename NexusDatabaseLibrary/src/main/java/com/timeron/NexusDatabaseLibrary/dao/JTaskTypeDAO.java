package com.timeron.NexusDatabaseLibrary.dao;

import com.timeron.NexusDatabaseLibrary.Entity.JTaskType;

public class JTaskTypeDAO extends DaoImp<JTaskType> {

	public JTaskTypeDAO(Class<JTaskType> persistantClass) {
		super(persistantClass);
		// TODO Auto-generated constructor stub
	}

	public JTaskTypeDAO(){
		super(JTaskType.class);
	}
	
}
