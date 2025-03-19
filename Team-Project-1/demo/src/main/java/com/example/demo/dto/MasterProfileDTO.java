package com.example.demo.dto;

public record MasterProfileDTO(
        Long id,
        String email,
        String name,
        String phone,
        String photoUrl,
        Double rating,
        Integer experienceYears
) {}