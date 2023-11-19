package com.soft.apilab.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_labs")
public class Lab {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID labId;
    @Column(nullable = false)
    private char block;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int capacity;
}
