package com.soft.apilab.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LabDto {

    private char block;
    @NotEmpty
    private String name;
    @NotNull
    private int capacity;
}
