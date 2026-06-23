package com.heyavinay.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	
	
	// Constructor-based Dependency Injection
	public ProductController(ProductRepository productRepository){
		this.productRepository=productRepository;
		
		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody ProductRequest request) {
		var product =new Product();
		product.setName(request.name());
		product.setPrice(request.price());
		product.setStockQuantity(request.stockQuantity());
		return productRepository.save(product);
		
		
	}
	@GetMapping
	public List<Product>getAllProducts(){
		return productRepository.findAll();
		
	}	

}
