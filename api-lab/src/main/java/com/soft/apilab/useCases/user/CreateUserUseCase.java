package com.soft.apilab.useCases.user;

import com.soft.apilab.dtos.InstructorDto;
import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.models.User;
import com.soft.apilab.repositories.UserRepository;
import com.soft.apilab.useCases.instructor.CreateInstructorUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    final UserRepository userRepository;
    final CreateInstructorUseCase createInstructorUseCase;

    public CreateUserUseCase(UserRepository userRepository, CreateInstructorUseCase createInstructorUseCase){
        this.userRepository = userRepository;
        this.createInstructorUseCase = createInstructorUseCase;
    }

    public User createNewUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setName(userDto.getName());
        instructorDto.setInstructorId(UserDto.gerarInstructorId(5));
        var instructor = createInstructorUseCase.createNewInstructor(instructorDto);

        user.setInstructor(instructor);
        return userRepository.save(user);
    }

}
