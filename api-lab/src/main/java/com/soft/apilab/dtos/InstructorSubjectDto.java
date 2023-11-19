package com.soft.apilab.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class InstructorSubjectDto {

    private String instructorId;
    private UUID subjectId;
}
