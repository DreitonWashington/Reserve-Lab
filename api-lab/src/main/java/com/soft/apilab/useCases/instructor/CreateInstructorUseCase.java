package com.soft.apilab.useCases.instructor;

import com.soft.apilab.dtos.InstructorDto;
import com.soft.apilab.models.Instructor;
import com.soft.apilab.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateInstructorUseCase {

    final InstructorRepository instructorRepository;

    public CreateInstructorUseCase(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor createNewInstructor(InstructorDto instructorDto){
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorDto.getInstructorId());
        instructor.setName(instructorDto.getName());
        System.out.print(instructor.getInstructorId());
        return instructorRepository.save(instructor);
    }
}
