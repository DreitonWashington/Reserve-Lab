package com.soft.apilab.useCases.subject;

import com.soft.apilab.dtos.SubjectDto;
import com.soft.apilab.models.Subject;
import com.soft.apilab.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateSubjectUseCase {

    final SubjectRepository subjectRepository;

    public CreateSubjectUseCase(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Subject createNewSubject(SubjectDto subjectDto){
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        if(subject.getName().isEmpty()){
            throw new IllegalArgumentException("Subject name cannot be null or empty");
        }
        return subjectRepository.save(subject);
    }

}
