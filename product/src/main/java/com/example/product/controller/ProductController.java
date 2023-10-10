package com.example.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entities.Product;
import com.example.product.request.ProductPostRequestBody;
import com.example.product.request.ProductPutRequestBody;
import com.example.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Product> save(@RequestBody @Valid ProductPostRequestBody productPostRequestBody){
		return new ResponseEntity<>(productService.save(productPostRequestBody), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<List<Product>> listAll(){
		return ResponseEntity.ok(productService.listAllNonPageable());
	}
	
	@GetMapping
	public ResponseEntity<Page<Product>> list(Pageable pageable){
		return ResponseEntity.ok(productService.findAll(pageable));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable UUID id){
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Product>> findByName(@RequestParam String name){
		return ResponseEntity.ok(productService.findByName(name));
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody @Valid ProductPutRequestBody productPutRequestBody){
		productService.replace(productPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
