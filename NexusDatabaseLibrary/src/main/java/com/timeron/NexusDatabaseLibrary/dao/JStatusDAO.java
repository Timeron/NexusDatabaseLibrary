package com.timeron.NexusDatabaseLibrary.dao;

import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.JStatus;

@Repository
public class JStatusDAO extends DaoImp<JStatus>  {
	
	public JStatusDAO(){
		super(JStatus.class);
	}

}
