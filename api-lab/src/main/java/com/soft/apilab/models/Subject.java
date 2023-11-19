package com.soft.apilab.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_subjects")
@Data
public class Subject {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID subjectId;
    @Column(nullable = false)
    private String name;
}
