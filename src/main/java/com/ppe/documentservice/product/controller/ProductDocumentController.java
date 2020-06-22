package com.ppe.documentservice.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppe.documentservice.product.entity.ProductDocumentEntity;
import com.ppe.documentservice.product.model.Product;
import com.ppe.documentservice.product.repository.ProductDocumentRepository;

@RestController
@RequestMapping("/shopping-products")
public class ProductDocumentController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductDocumentController.class);
	
	
	@Autowired
	private ProductDocumentRepository productMongoRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * This API returns the list of products 
	 * 
	 * @return
	 */

	@RequestMapping("/products")
	public List<Product> getProducts() {

		List<ProductDocumentEntity> productsMongo = productMongoRepository.findAll();

		List<Product> products = productsMongo.stream().map(productMongo -> {
			Product product = null;
			try {
				 product = objectMapper.readValue(productMongo.getDetail(), Product.class);

			} catch (JsonProcessingException e) { 
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return product;
		}).collect(Collectors.toList());


		products.stream().forEach(product-> logger.info(product.toString()));
		return products;
	}
	
	/**
	 * 
	 * This API returns the details of a product
	 * 
	 * @param id
	 * @return
	 */
	//@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") long id) {
		ProductDocumentEntity productMongo = productMongoRepository.findById(id);
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
