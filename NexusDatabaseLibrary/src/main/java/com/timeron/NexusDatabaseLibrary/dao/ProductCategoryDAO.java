package com.timeron.NexusDatabaseLibrary.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.timeron.NexusDatabaseLibrary.Entity.ProductCategory;
import com.timeron.NexusDatabaseLibrary.helper.JpaHelper;

@Repository
public class ProductCategoryDAO extends DaoImp<ProductCategory>{
	
	public ProductCategoryDAO() {
		super(ProductCategory.class);
	}

	static Logger log = Logger.getLogger(ProductCategoryDAO.class.getName());

	@SuppressWarnings("unchecked")
	public List<ProductCategory> getByDescription(String description) {
		List<ProductCategory> productCategory = new ArrayList<ProductCategory>();
		Session session = JpaHelper.createSession(entityManager, persistantClass);
		String hql = "FROM ProductCategory WHERE upper(description) = '"+description.toUpperCase()+"'";
		
		Query query = session.createQuery(hql);
		productCategory = (List<ProductCategory>) query.list();
		
		if (productCategory.size() > 0) {
			return productCategory;
		} else {
			List<ProductCategory> emptyList = Collections.emptyList();
			return emptyList;
		}
		
	}

}
