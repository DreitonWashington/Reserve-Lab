package com.soft.apilab.useCases.reserve;

import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckIfReserveExistUseCase {

    final ReserveRepository reserveRepository;

    public CheckIfReserveExistUseCase(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    public Boolean checkIfReserveExistByDate(LocalDateTime date, UUID labId){
        return reserveRepository.checkIfReserveExistByDate(date, labId);
    }

    public Set<String> extractedTimes(LocalDateTime date, UUID labId) {
        return reserveRepository.extractHourFromReservesDate(date, labId);
    }
}
