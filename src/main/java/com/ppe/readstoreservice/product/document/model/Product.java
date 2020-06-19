package com.ppe.readstoreservice.product.document.model;

import lombok.Data;

@Data
public class Product {

	private Long id;
	private String name;
	private String description;
	private int price;
	private int instock;
}
