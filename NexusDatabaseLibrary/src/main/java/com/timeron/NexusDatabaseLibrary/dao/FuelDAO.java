package com.timeron.NexusDatabaseLibrary.dao;

import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.Fuel;

@Repository
public class FuelDAO extends DaoImp<Fuel> {
	
	public FuelDAO() {
		super(Fuel.class);
	}
	
	

}
