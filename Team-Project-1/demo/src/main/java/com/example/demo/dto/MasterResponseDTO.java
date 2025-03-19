package com.example.demo.dto;

public record MasterResponseDTO(Long id, Long userId, String name, String email, String phone, String photoUrl, Double rating, Integer experienceYears) {}