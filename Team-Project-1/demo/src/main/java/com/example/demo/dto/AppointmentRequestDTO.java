package com.example.demo.dto;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        Long serviceId,
        Long masterId,
        LocalDateTime dateTime,
        String clientMessage,
        Boolean wantsPhoto
) {}