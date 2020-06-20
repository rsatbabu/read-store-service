package com.ppe.documentservice.product.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppe.documentservice.product.entity.ProductDocumentEntity;
import com.ppe.documentservice.product.model.Product;
import com.ppe.documentservice.product.repository.ProductDocumentRepository;

@WebMvcTest(ProductDocumentController.class)
public class ProductDocumentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductDocumentRepository productMongoRepository;
	
	@MockBean
	private ObjectMapper objectMapper;
	
	private String detail = "{\"id\":1,\"name\":\"Gloves\",\"description\":\"Quantity in 1 Box - 100 Gloves = 50 pairs in 1 box\",\"price\":50,\"instock\":12}";

	
	@Test
	public void getProductsTest() throws Exception {
		when(productMongoRepository.findAll()).thenReturn(getProductDocumentEntities());
		when(objectMapper.readValue(detail,Product.class)).thenReturn(getProduct());
		
		this.mockMvc.perform(get("/products")).andExpect(status().isOk());
	}

	@Test
	public void getProductTest() throws Exception{
		when(productMongoRepository.findById(1L)).thenReturn(getDocumentEntity());
		when(objectMapper.readValue(detail,Product.class)).thenReturn(getProduct());
		
		this.mockMvc.perform(get("/products/1")).andExpect(status().isOk());	
	}
	private List<ProductDocumentEntity> getProductDocumentEntities(){
		List<ProductDocumentEntity> productDocumentEntities = new ArrayList<>();
		productDocumentEntities.add(getDocumentEntity());
		return productDocumentEntities;
	}
	private List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		products.add(getProduct());
		return products;
	}
	private ProductDocumentEntity getDocumentEntity() {
		ProductDocumentEntity productDocumentEntity = new ProductDocumentEntity();
		productDocumentEntity.setId(1L);
		productDocumentEntity.setDetail(detail);
		return productDocumentEntity;
	}
	private Product getProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setDescription("Quantity in 1 Box - 100 Gloves = 50 pairs in 1 box");
		product.setName("Gloves");
		product.setInstock(100);
		product.setPrice(10);
		return product;
	}
}
