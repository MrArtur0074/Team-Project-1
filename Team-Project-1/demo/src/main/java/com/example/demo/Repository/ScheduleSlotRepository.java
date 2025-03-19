package com.example.demo.repository;

import com.example.demo.entity.Master;
import com.example.demo.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
    List<ScheduleSlot> findByMaster(Master master);
}