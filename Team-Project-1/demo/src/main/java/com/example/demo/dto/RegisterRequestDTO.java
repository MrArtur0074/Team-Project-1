package com.example.demo.dto;

public record RegisterRequestDTO(
        String email,
        String password,
        String name,
        String phone
) {}