package com.example.demo.dto;

public record ClientTagResponseDTO(
        Long id,
        Long clientId,
        String tagText
) {}