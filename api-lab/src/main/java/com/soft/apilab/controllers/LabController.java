package com.soft.apilab.controllers;

import com.soft.apilab.dtos.LabDto;
import com.soft.apilab.models.Lab;
import com.soft.apilab.specifications.LabSpecification;
import com.soft.apilab.useCases.lab.CreateLabUseCase;
import com.soft.apilab.useCases.lab.FindAllLabsUseCase;
import com.soft.apilab.useCases.lab.FindLabUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/labs")
public class LabController {

    final CreateLabUseCase createLabUseCase;
    final FindLabUseCase findLabUseCase;
    final FindAllLabsUseCase findAllLabsUseCase;

    public LabController(CreateLabUseCase createLabUseCase, FindLabUseCase findLabUseCase,
                         FindAllLabsUseCase findAllLabsUseCase){
        this.createLabUseCase = createLabUseCase;
        this.findLabUseCase = findLabUseCase;
        this.findAllLabsUseCase = findAllLabsUseCase;
    }

    @PostMapping("/create-lab")
    public ResponseEntity<Lab> createLab(@Valid @RequestBody LabDto labDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createLabUseCase.createNewLab(labDto));
    }

    @GetMapping("/{labId}")
    public ResponseEntity<Lab> findLab(@PathVariable(value = "labId")UUID labId){
        return ResponseEntity.status(HttpStatus.OK).body(findLabUseCase.findLab(labId));
    }

    @GetMapping
    public ResponseEntity<Page<Lab>> findAllLabs(LabSpecification spec, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(findAllLabsUseCase.findAllLabs(spec, pageable));
    }

}
