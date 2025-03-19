package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vacancy_parameters")
@Data
public class VacancyParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "is_required")
    private Boolean isRequired;
}