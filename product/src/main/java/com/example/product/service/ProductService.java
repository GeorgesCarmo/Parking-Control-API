package com.example.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product.entities.Product;
import com.example.product.exception.BadRequestException;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import com.example.product.request.ProductPostRequestBody;
import com.example.product.request.ProductPutRequestBody;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService{
	
	private final ProductRepository productRepository;

	@Transactional
	public Product save(ProductPostRequestBody productPostRequestBody) {
		return productRepository.save(ProductMapper.INSTANCE.toProduct(productPostRequestBody));
	}
	
	public List<Product> listAllNonPageable(){
		return productRepository.findAll();
	}
	
	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}
	
	public Product findById(UUID id) {
		return productRepository.findById(id).orElseThrow(() -> new BadRequestException("Product not found"));
	}
	
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}

	public void replace(ProductPutRequestBody productPutRequestBody) {
		Product savedProduct = findById(productPutRequestBody.getIdProduct());
		Product product = ProductMapper.INSTANCE.toProduct(productPutRequestBody);
		product.setIdProduct(savedProduct.getIdProduct());
		productRepository.save(product);
	}
	
	public void delete(UUID id) {
		productRepository.delete(findById(id));
	}
}
