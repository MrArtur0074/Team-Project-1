package com.example.demo.dto;

public record UserUpdateRequestDTO(
        String name,
        String email,
        String phone,
        String role
) {}