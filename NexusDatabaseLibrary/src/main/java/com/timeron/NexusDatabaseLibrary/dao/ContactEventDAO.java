package com.timeron.NexusDatabaseLibrary.dao;

import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ContactEvent;

@Repository
public class ContactEventDAO extends DaoImp<ContactEvent> {

	public ContactEventDAO() {
		super(ContactEvent.class);
	}

	
	
}
