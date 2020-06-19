package com.ppe.readstoreservice.product.document.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productmongo")
@Data
public class ProductMongoEntity {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String detail;
	public ProductMongoEntity(Long id, String detail) {
		super();
		this.id = id;
		this.detail = detail;
	}
	public ProductMongoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}
