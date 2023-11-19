package com.soft.apilab.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class UserDto {

    private String name;
    private String username;
    private String email;
    private String password;
    private String instructorId;


    public final static String gerarInstructorId(int size) {
        String caracteresValidos = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(caracteresValidos.length());
            char caracterAleatorio = caracteresValidos.charAt(index);
            codigo.append(caracterAleatorio);
        }
        return codigo.toString();
    }
}
