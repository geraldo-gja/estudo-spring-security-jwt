package com.exemple.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.exemple.entity.Product;
import com.exemple.repository.ProductRepository;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public void saveProduct(@RequestBody Product product){
        this.productRepository.save(product);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
    	List<Product> list = productRepository.findAll();
    	return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")  //TODO não funcionou
    public void delete(@PathVariable Long id){
      this.productRepository.deleteById(id);
    }

}