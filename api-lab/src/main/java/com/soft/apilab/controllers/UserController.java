package com.soft.apilab.controllers;

import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.models.User;
import com.soft.apilab.useCases.user.CreateUserUseCase;
import com.soft.apilab.useCases.user.FindAllUsersUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
@RestController
public class UserController {

    final CreateUserUseCase createUserUseCase;
    final FindAllUsersUseCase findAllUsersUseCase;

    public UserController(CreateUserUseCase createUserUseCase, FindAllUsersUseCase findAllUsersUseCase){
        this.createUserUseCase = createUserUseCase;
        this.findAllUsersUseCase = findAllUsersUseCase;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(findAllUsersUseCase.findAllUsers(pageable));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.createNewUser(userDto));
    }

}
