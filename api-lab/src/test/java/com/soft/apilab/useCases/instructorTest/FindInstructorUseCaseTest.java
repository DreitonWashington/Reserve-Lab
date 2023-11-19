package com.soft.apilab.useCases.instructorTest;

import com.soft.apilab.dtos.InstructorDto;
import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.models.Instructor;
import com.soft.apilab.useCases.instructor.CreateInstructorUseCase;
import com.soft.apilab.useCases.instructor.FindInstructorUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class FindInstructorUseCaseTest {

    @Autowired
    CreateInstructorUseCase createInstructorUseCase;
    @Autowired
    FindInstructorUseCase findInstructorUseCase;

    private String instructorId;

    @BeforeEach
    void createDataToTest(){
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setName("Maria Silva");
        instructorDto.setInstructorId(UserDto.gerarInstructorId(5));
        this.instructorId = createInstructorUseCase.createNewInstructor(instructorDto).getInstructorId();
    }

    @Test
    void findInstructorUseCaseSuccessTest(){
        Instructor instructor = findInstructorUseCase.findInstructor(this.instructorId);
        Assertions.assertEquals("Maria Silva", instructor.getName());
    }

    @Test
    void findInstructorUseCaseFailNotFoundTest(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            findInstructorUseCase.findInstructor("g2ebb");
        });
    }
}
