package com.soft.apilab.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReserveDto {

    private String instructorId;
    private UUID labId;
    private UUID subjectId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
