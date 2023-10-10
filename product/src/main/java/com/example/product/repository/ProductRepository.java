package com.example.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{
	List<Product> findByName(String name);
}
