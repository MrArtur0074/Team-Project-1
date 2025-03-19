package com.example.demo.dto;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        Long clientId,
        Long serviceId,
        Long masterId,
        LocalDateTime dateTime,
        String status,
        String clientMessage,
        String photoUrl
) {}