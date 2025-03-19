package com.example.demo.dto;

import java.time.LocalTime;

public record ScheduleSlotRequestDTO(
        Integer dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}