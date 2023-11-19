package com.soft.apilab.controllers;

import com.soft.apilab.configs.security.JwtProvider;
import com.soft.apilab.dtos.JwtDto;
import com.soft.apilab.dtos.LoginDto;
import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.repositories.UserRepository;
import com.soft.apilab.useCases.user.CreateUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final JwtProvider jwtProvider;
    final AuthenticationManager authenticationManager;
    final CreateUserUseCase createUserUseCase;

    public AuthenticationController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                    JwtProvider jwtProvider, AuthenticationManager authenticationManager,
                                    CreateUserUseCase createUserUseCase){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.createNewUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }
}
