package com.example.smallWebSecurityApp.demo.service;

import com.example.smallWebSecurityApp.demo.entity.product.Product;
import com.example.smallWebSecurityApp.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

}
