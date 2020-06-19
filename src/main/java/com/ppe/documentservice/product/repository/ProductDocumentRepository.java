package com.ppe.documentservice.product.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ppe.documentservice.product.entity.ProductDocumentEntity;


  public interface ProductDocumentRepository extends CrudRepository<ProductDocumentEntity,Long> {
	  	ProductDocumentEntity findById(long id);
		List<ProductDocumentEntity> findAll();
  }
 