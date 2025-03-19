package com.example.demo.dto;

public record MasterServiceResponseDTO(
        Long id,
        Long masterId,
        Long salonServiceId,
        String serviceName,
        Double price
) {}