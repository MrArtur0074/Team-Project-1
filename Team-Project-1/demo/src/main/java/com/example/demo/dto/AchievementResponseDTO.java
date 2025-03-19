package com.example.demo.dto;

public record AchievementResponseDTO(
        Long id,
        String name,
        String description,
        String icon
) {}