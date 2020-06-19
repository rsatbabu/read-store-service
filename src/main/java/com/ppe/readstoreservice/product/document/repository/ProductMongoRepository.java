package com.ppe.readstoreservice.product.document.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ppe.readstoreservice.product.document.entity.ProductMongoEntity;


  public interface ProductMongoRepository extends CrudRepository<ProductMongoEntity,Long> {
	  	ProductMongoEntity findById(long id);
		List<ProductMongoEntity> findAll();
  }
 