package com.example.demo.dto;

public record ReviewResponseDTO(
        Long id,
        Long appointmentId,
        Long clientId,
        Integer rating,
        String comment
) {}