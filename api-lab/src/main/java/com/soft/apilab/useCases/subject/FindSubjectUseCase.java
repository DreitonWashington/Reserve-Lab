package com.soft.apilab.useCases.subject;

import com.soft.apilab.models.Subject;
import com.soft.apilab.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindSubjectUseCase {

    final SubjectRepository subjectRepository;

    public FindSubjectUseCase(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Subject findSubject(UUID subjectId){
        return this.subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject Not Found!"));
    }
}
