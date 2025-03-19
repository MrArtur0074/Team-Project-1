package com.example.demo.repository;

import com.example.demo.entity.ShiftCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShiftCapacityRepository extends JpaRepository<ShiftCapacity, Long> {
    @Query("SELECT s FROM ShiftCapacity s LIMIT 1")
    Optional<ShiftCapacity> findFirst();
}