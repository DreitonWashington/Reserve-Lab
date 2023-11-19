package com.soft.apilab.controllers;

import com.soft.apilab.dtos.InstructorDto;
import com.soft.apilab.dtos.InstructorSubjectDto;
import com.soft.apilab.models.Instructor;
import com.soft.apilab.models.Reserve;
import com.soft.apilab.models.Subject;
import com.soft.apilab.useCases.instructor.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/instructors")
public class InstructorController {

    final CreateInstructorUseCase createInstructorUseCase;
    final FindInstructorUseCase findInstructorUseCase;
    final AssociateProfessorWithSubjectUseCase associateProfessorWithSubjectUseCase;
    final FindSubjectsByInstructorUseCase findSubjectsByInstructorUseCase;
    final FindAllReservesFromInstructorUserUseCase findAllReservesFromInstructorUserUseCase;

    public InstructorController(CreateInstructorUseCase createInstructorUseCase,
                                FindInstructorUseCase findInstructorUseCase,
                                AssociateProfessorWithSubjectUseCase associateProfessorWithSubjectUseCase,
                                FindSubjectsByInstructorUseCase findSubjectsByInstructorUseCase,
                                FindAllReservesFromInstructorUserUseCase findAllReservesFromInstructorUserUseCase) {
        this.createInstructorUseCase = createInstructorUseCase;
        this.findInstructorUseCase = findInstructorUseCase;
        this.associateProfessorWithSubjectUseCase = associateProfessorWithSubjectUseCase;
        this.findSubjectsByInstructorUseCase = findSubjectsByInstructorUseCase;
        this.findAllReservesFromInstructorUserUseCase = findAllReservesFromInstructorUserUseCase;
    }

    @PostMapping("/create-instructor")
    public ResponseEntity<Instructor> createInstructor(@RequestBody InstructorDto instructorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createInstructorUseCase.createNewInstructor(instructorDto));
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> findInstructorById(@PathVariable(value = "instructorId") String instructorId){
        return ResponseEntity.status(HttpStatus.OK).body(findInstructorUseCase.findInstructor(instructorId));
    }

    @PostMapping("/{instructorId}/associate-subject")
    public ResponseEntity<InstructorSubjectDto> associateInstructorWithSubject(
            @PathVariable(value = "instructorId")String instructorId,
            @RequestBody InstructorSubjectDto instructorSubjectDto){
        instructorSubjectDto.setInstructorId(instructorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(associateProfessorWithSubjectUseCase.associateProfessorSubject(instructorSubjectDto));
    }

    @GetMapping("/{instructorId}/subjects")
    public ResponseEntity<Set<Subject>> findSubjectsByInstructor(@PathVariable(value = "instructorId")String instructorId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(findSubjectsByInstructorUseCase.findSubjectsByInstructor(instructorId));
    }

    @GetMapping("/{userId}/reserves")
    public ResponseEntity<Page<Reserve>> findAllReservesFromInstructorUser(@PathVariable(value = "userId") UUID userId,
                                                                           Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                findAllReservesFromInstructorUserUseCase.findAllReservesFromInstructorUser(userId, pageable));
    }
}
