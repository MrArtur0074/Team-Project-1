package com.example.demo.dto;

public record ClientProfileDTO(
        Long id,
        String email,
        String name,
        String phone,
        String photoUrl
) {}