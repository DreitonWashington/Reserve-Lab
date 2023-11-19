package com.soft.apilab.useCases.instructor;

import com.soft.apilab.models.Instructor;
import com.soft.apilab.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

@Service
public class FindInstructorUseCase {

    final InstructorRepository instructorRepository;

    public FindInstructorUseCase(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor findInstructor(String instructorId){
        return instructorRepository.findById(instructorId).orElseThrow( () -> new RuntimeException("Instructor Not Found!"));
    }
}
