package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shift_capacity")
@Data
public class ShiftCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shift_name")
    private String shiftName;

    @Column(name = "max_masters")
    private Integer maxMasters;
}