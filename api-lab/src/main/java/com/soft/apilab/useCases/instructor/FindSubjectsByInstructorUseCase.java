package com.soft.apilab.useCases.instructor;

import com.soft.apilab.models.Subject;
import com.soft.apilab.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FindSubjectsByInstructorUseCase {

    final SubjectRepository subjectRepository;

    public FindSubjectsByInstructorUseCase(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Set<Subject> findSubjectsByInstructor(String instructorId){
        return subjectRepository.findSubjectsByInstructor(instructorId);
    }
}
