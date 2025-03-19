package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "schedule_slots")
@Data
public class ScheduleSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    private Integer dayOfWeek; // 1-7
    private LocalTime startTime;
    private LocalTime endTime;
}