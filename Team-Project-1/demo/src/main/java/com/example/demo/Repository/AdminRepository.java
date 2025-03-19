package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientIdAndStatus(Long clientId, String status);
    List<Appointment> findByMasterIdAndStatus(Long masterId, String status);
}