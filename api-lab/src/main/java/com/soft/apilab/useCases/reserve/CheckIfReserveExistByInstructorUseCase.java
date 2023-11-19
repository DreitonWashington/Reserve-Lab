package com.soft.apilab.useCases.reserve;

import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckIfReserveExistByInstructorUseCase {

    final ReserveRepository reserveRepository;

    public CheckIfReserveExistByInstructorUseCase(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    public Boolean checkIfReserveExistByInstructor(String instructor_id){
        return reserveRepository.checkIfReserveExistByInstructor(instructor_id);
    }
}
