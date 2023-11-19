package com.soft.apilab.useCases.reserve;

import com.soft.apilab.models.Reserve;
import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindReserveUseCase {

    final ReserveRepository reserveRepository;

    public FindReserveUseCase(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public Reserve findReserve(UUID reserveId){
        return reserveRepository.findById(reserveId).orElseThrow( () -> new RuntimeException("Reserve Not Found!"));
    }
}
