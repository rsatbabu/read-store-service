package com.ppe.readstoreservice.product.document.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppe.readstoreservice.product.document.entity.ProductMongoEntity;
import com.ppe.readstoreservice.product.document.model.Product;
import com.ppe.readstoreservice.product.document.repository.ProductMongoRepository;

@RestController
public class ProductDocumentController {
	@Autowired
	private ProductMongoRepository productMongoRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/products")
	public List<Product> getProducts() {

		List<ProductMongoEntity> productsMongo = productMongoRepository.findAll();

		List<Product> products = productsMongo.stream().map(productMongo -> {
			Product product = null;
			try {
				 product = objectMapper.readValue(productMongo.getDetail(), Product.class);

			} catch (JsonProcessingException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return product;
		}).collect(Collectors.toList());

		if (products == null || products.size() == 0)
			System.out.println("empty");
		products.stream().forEach(product-> System.out.println(product.toString()));
		return products;
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") long id) {
		ProductMongoEntity productMongo = productMongoRepository.findById(id);
		Product product = null;
		try {
			 product = objectMapper.readValue(productMongo.getDetail(), Product.class);

		} catch (JsonProcessingException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return product;

	}
}
