package com.ppe.documentservice.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productmongo")
@Data
public class ProductDocumentEntity {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String detail;
	public ProductDocumentEntity(Long id, String detail) {
		super();
		this.id = id;
		this.detail = detail;
	}
	public ProductDocumentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}
