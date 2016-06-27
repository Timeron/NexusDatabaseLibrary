package com.timeron.NexusDatabaseLibrary.dao;

import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.Product;

@Repository
public class ProductDAO extends DaoImp<Product>{

	public ProductDAO(Class<Product> persistantClass) {
		super(persistantClass);
	}

	public ProductDAO() {
		super(Product.class);
	}
	
	

	
}
