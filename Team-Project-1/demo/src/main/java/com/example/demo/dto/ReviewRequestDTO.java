package com.example.demo.dto;

public record ReviewRequestDTO(
        Integer rating,
        String comment
) {}