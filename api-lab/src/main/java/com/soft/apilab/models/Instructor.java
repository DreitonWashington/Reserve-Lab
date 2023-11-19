package com.soft.apilab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_instructors")
public class Instructor {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private UUID id;
    @Id
    @Column(nullable = false, unique = true)
    private String instructorId;
    @Column(nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany
    private Set<Subject> subjects;

}
