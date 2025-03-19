package com.example.demo.repository;

import com.example.demo.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {
    Optional<Master> findByUserId(Long userId);
}