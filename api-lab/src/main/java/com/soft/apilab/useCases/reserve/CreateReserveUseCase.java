package com.soft.apilab.useCases.reserve;

import com.soft.apilab.dtos.ReserveDto;
import com.soft.apilab.enums.ReserveStatus;
import com.soft.apilab.models.Reserve;
import com.soft.apilab.repositories.ReserveRepository;
import com.soft.apilab.useCases.instructor.FindInstructorUseCase;
import com.soft.apilab.useCases.lab.FindLabUseCase;
import com.soft.apilab.useCases.subject.FindSubjectUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateReserveUseCase {

    final FindInstructorUseCase findInstructorUseCase;
    final FindLabUseCase findLabUseCase;
    final ReserveRepository reserveRepository;
    final CheckIfReserveExistUseCase checkIfReserveExistUseCase;
    final FindSubjectUseCase findSubjectUseCase;

    public CreateReserveUseCase(FindInstructorUseCase findInstructorUseCase, FindLabUseCase findLabUseCase,
                                ReserveRepository reserveRepository, CheckIfReserveExistUseCase checkIfReserveExistUseCase,
                                FindSubjectUseCase findSubjectUseCase){
        this.findInstructorUseCase = findInstructorUseCase;
        this.findLabUseCase = findLabUseCase;
        this.reserveRepository = reserveRepository;
        this.checkIfReserveExistUseCase = checkIfReserveExistUseCase;
        this.findSubjectUseCase = findSubjectUseCase;
    }

    public Reserve createNewReserve(ReserveDto reserveDto){
        if(checkIfReserveExistUseCase.checkIfReserveExistByDate(reserveDto.getStartTime(), reserveDto.getLabId())){
            throw new RuntimeException("Already have reserve with this date and time or time");
        }

        Reserve reserve = new Reserve();
        reserve.setInstructor(findInstructorUseCase.findInstructor(reserveDto.getInstructorId()));
        reserve.setLab(findLabUseCase.findLab(reserveDto.getLabId()));
        reserve.setSubject(findSubjectUseCase.findSubject(reserveDto.getSubjectId()));
        reserve.setStartTime(reserveDto.getStartTime());
        reserve.setEndTime(reserveDto.getEndTime());
        reserve.setReserveStatus(ReserveStatus.RESERVED);

        return reserveRepository.save(reserve);
    }
}
