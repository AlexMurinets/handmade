package com.example.handmadeBackend.repository;

import com.example.handmadeBackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
