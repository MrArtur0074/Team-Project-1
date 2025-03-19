package com.example.demo.repository;

import com.example.demo.entity.ClientTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientTagRepository extends JpaRepository<ClientTag, Long> {
    List<ClientTag> findByMasterId(Long masterId);
    List<ClientTag> findByClientId(Long clientId);
}