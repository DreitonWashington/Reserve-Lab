package com.soft.apilab.useCases.instructor;

import com.soft.apilab.dtos.InstructorSubjectDto;
import com.soft.apilab.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociateProfessorWithSubjectUseCase {

    final InstructorRepository instructorRepository;

    public AssociateProfessorWithSubjectUseCase(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    public InstructorSubjectDto associateProfessorSubject(InstructorSubjectDto instructorSubjectDto) {
        if(this.instructorRepository
                .associateProfessorWithSubject(instructorSubjectDto.getInstructorId(),
                        instructorSubjectDto.getSubjectId())){
            return instructorSubjectDto;
        }
        throw new RuntimeException("Error trying associate professor with subjective");
    }
}
