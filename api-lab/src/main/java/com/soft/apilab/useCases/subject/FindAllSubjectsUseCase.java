package com.soft.apilab.useCases.subject;

import com.soft.apilab.models.Subject;
import com.soft.apilab.repositories.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllSubjectsUseCase {

    final SubjectRepository subjectRepository;

    public FindAllSubjectsUseCase(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Page<Subject> findAllSubjects(Pageable pageable){
        return this.subjectRepository.findAll(pageable);
    }
}
