package com.example.handmadeBackend.security.repository;

import com.example.handmadeBackend.security.model.AuthenticationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRequestRepository extends JpaRepository<AuthenticationRequest, Long> {
    public boolean existsByIp(String ip);
    public AuthenticationRequest findByIp(String ip);
}
