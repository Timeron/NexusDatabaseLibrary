package com.timeron.NexusDatabaseLibrary.dao;

import com.timeron.NexusDatabaseLibrary.Entity.JProject;

public class JProjectDAO extends DaoImp<JProject> {

	public JProjectDAO(Class<JProject> persistantClass) {
		super(persistantClass);
	}

	public JProjectDAO() {
		super(JProject.class);
	}
	
}
