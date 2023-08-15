package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
}

