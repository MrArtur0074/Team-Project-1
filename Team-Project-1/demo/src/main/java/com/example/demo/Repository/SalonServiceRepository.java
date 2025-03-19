package com.example.demo.repository;

import com.example.demo.entity.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonServiceRepository extends JpaRepository<SalonService, Long> {
}