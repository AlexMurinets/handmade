package com.example.handmadeBackend.repository;


import com.example.handmadeBackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
