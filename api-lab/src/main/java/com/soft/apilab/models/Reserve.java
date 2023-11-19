package com.soft.apilab.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soft.apilab.enums.ReserveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_reserves")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reserveId;
    @OneToOne
    private Instructor instructor;
    @OneToOne
    private Subject subject;
    @OneToOne
    private Lab lab;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;
}
