package com.example.handmadeBackend.repository;

import com.example.handmadeBackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
