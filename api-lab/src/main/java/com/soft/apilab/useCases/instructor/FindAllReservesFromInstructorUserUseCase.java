package com.soft.apilab.useCases.instructor;

import com.soft.apilab.models.Reserve;
import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindAllReservesFromInstructorUserUseCase {

    final ReserveRepository reserveRepository;

    public FindAllReservesFromInstructorUserUseCase(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    public Page<Reserve> findAllReservesFromInstructorUser(UUID userId, Pageable pageable){
        return this.reserveRepository.findAllReservesFromUser(userId, pageable);
    }
}
