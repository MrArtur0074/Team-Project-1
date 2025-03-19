package com.example.demo.dto;

public record ClientTagRequestDTO(
        Long clientId,
        String tagText
) {}