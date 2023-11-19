package com.soft.apilab.controllers;

import com.soft.apilab.dtos.SubjectDto;
import com.soft.apilab.models.Subject;
import com.soft.apilab.useCases.subject.CreateSubjectUseCase;
import com.soft.apilab.useCases.subject.FindAllSubjectsUseCase;
import com.soft.apilab.useCases.subject.FindSubjectUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    final CreateSubjectUseCase createSubjectUseCase;
    final FindAllSubjectsUseCase findAllSubjectsUseCase;
    final FindSubjectUseCase findSubjectUseCase;

    public SubjectController(CreateSubjectUseCase createSubjectUseCase, FindAllSubjectsUseCase findAllSubjectsUseCase,
                             FindSubjectUseCase findSubjectUseCase){
        this.createSubjectUseCase = createSubjectUseCase;
        this.findAllSubjectsUseCase = findAllSubjectsUseCase;
        this.findSubjectUseCase = findSubjectUseCase;
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createSubjectUseCase.createNewSubject(subjectDto));
    }

    @GetMapping
    public ResponseEntity<Page<Subject>> findAllSubjects(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(findAllSubjectsUseCase.findAllSubjects(pageable));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> findSubject(@PathVariable(value = "subjectId")UUID subjectId){
        return ResponseEntity.status(HttpStatus.OK).body(findSubjectUseCase.findSubject(subjectId));
    }
}
