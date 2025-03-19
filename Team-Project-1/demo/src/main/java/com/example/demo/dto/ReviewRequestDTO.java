package com.example.demo.dto;

public record ReviewRequestDTO(
        Long appointmentId,
        Integer rating,
        String comment
) {}