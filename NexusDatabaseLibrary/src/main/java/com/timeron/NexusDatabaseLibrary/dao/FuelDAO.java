package com.timeron.NexusDatabaseLibrary.dao;

import com.timeron.NexusDatabaseLibrary.Entity.Fuel;

public class FuelDAO extends DaoImp<Fuel> {
	
	public FuelDAO(Class<Fuel> persistantClass) {
		super(persistantClass);
	}
	
	public FuelDAO() {
		super(Fuel.class);
	}
	
	

}
