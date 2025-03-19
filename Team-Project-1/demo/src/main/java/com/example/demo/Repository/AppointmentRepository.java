package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Client;
import com.example.demo.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByMaster(Master master);
    List<Appointment> findByClient(Client client);
}