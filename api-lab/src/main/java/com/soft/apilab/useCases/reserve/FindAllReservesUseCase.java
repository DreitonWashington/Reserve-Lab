package com.soft.apilab.useCases.reserve;

import com.soft.apilab.models.Reserve;
import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FindAllReservesUseCase {

    final ReserveRepository reserveRepository;

    public FindAllReservesUseCase(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    public Page<Reserve> findAllReserves(Specification<Reserve> spec, Pageable pageable){
        return reserveRepository.findAll(spec, pageable);
    }

    public Page<Reserve> findAllReserves(Pageable pageable){
        return reserveRepository.findAll(pageable);
    }

}
