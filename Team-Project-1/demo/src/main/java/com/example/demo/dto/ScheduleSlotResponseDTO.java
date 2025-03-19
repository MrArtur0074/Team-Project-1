package com.example.demo.dto;

import java.time.LocalTime;

public record ScheduleSlotResponseDTO(
        Long id,
        Integer dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
