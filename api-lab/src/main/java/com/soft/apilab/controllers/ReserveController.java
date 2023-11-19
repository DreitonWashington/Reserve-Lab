package com.soft.apilab.controllers;

import com.soft.apilab.dtos.ReserveDto;
import com.soft.apilab.models.Reserve;
import com.soft.apilab.specifications.ReserveSpecification;
import com.soft.apilab.useCases.reserve.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/reserves")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReserveController {

    final CreateReserveUseCase createReserveUseCase;
    final FindReserveUseCase findReserveUseCase;
    final CheckIfReserveExistUseCase checkIfReserveExistUseCase;
    final FindAllReservesUseCase findAllReservesUseCase;

    final CheckIfReserveExistByInstructorUseCase checkIfReserveExistByInstructorUseCase;

    public ReserveController(CreateReserveUseCase createReserveUseCase, FindReserveUseCase findReserveUseCase,
                             CheckIfReserveExistUseCase checkIfReserveExistUseCase, FindAllReservesUseCase findAllReservesUseCase,
                             CheckIfReserveExistByInstructorUseCase checkIfReserveExistByInstructorUseCase){
        this.createReserveUseCase = createReserveUseCase;
        this.findReserveUseCase = findReserveUseCase;
        this.checkIfReserveExistUseCase = checkIfReserveExistUseCase;
        this.findAllReservesUseCase = findAllReservesUseCase;
        this.checkIfReserveExistByInstructorUseCase = checkIfReserveExistByInstructorUseCase;
    }

    @PostMapping("/create-reserve")
    public ResponseEntity<Reserve> createReserve(@RequestBody ReserveDto reserveDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(createReserveUseCase.createNewReserve(reserveDto));
    }

    @GetMapping("/{reserveId}")
    public ResponseEntity<Reserve> findReserve(@PathVariable(value = "reserveId")UUID reserveId){
        return ResponseEntity.status(HttpStatus.OK).body(findReserveUseCase.findReserve(reserveId));
    }

    @GetMapping(value = "/check-if-reserve-exist")
    public ResponseEntity<Boolean> checkIfReserveExist(
            @RequestParam(value = "startTime", required = true) String startTime,
            @RequestParam(value = "labId", required = true) String labId,
            @RequestParam(value = "instructorId", required = false) String instructorId){

        if(instructorId != null) return ResponseEntity.status(HttpStatus.OK).body(checkIfReserveExistByInstructorUseCase.checkIfReserveExistByInstructor(instructorId));
        return ResponseEntity.status(HttpStatus.OK).body(checkIfReserveExistUseCase.checkIfReserveExistByDate(LocalDateTime.parse(startTime), UUID.fromString(labId)));
    }

    @GetMapping
    public ResponseEntity<Page<Reserve>> findAllReserves(ReserveSpecification spec, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(findAllReservesUseCase.findAllReserves(spec, pageable));
    }

    @GetMapping("/check-if-reserve-exist/extract-hours")
    public ResponseEntity<Set<String>> teste(
            @RequestParam(value = "startTime", required = true) String startTime,
            @RequestParam(value = "labId", required = true) String labId){
        return ResponseEntity.status(HttpStatus.OK).body(checkIfReserveExistUseCase.extractedTimes(LocalDateTime.parse(startTime), UUID.fromString(labId)));
    }
}
