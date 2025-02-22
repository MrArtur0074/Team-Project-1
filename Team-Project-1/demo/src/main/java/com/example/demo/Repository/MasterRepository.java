package com.example.demo.Repository;

import com.example.demo.Model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {
}