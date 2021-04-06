package com.example.smallWebSecurityApp.demo.repo;

import com.example.smallWebSecurityApp.demo.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
