package com.example.handmadeBackend.repository;

import com.example.handmadeBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
