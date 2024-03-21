package com.service.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
}
