package com.timeron.NexusDatabaseLibrary.dao;

import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.JTaskType;

@Repository
public class JTaskTypeDAO extends DaoImp<JTaskType> {

	public JTaskTypeDAO(){
		super(JTaskType.class);
	}
	
}
