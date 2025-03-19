package com.example.demo.repository;

import com.example.demo.entity.VacancyParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VacancyParametersRepository extends JpaRepository<VacancyParameters, Long> {
    @Query("SELECT v FROM VacancyParameters v LIMIT 1")
    Optional<VacancyParameters> findFirst();
}