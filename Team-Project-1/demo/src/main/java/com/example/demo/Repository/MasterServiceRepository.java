package com.example.demo.repository;

import com.example.demo.entity.Master;
import com.example.demo.entity.MasterService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterServiceRepository extends JpaRepository<MasterService, Long> {
    List<MasterService> findByMaster(Master master);
}