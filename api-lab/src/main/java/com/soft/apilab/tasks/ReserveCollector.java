package com.soft.apilab.tasks;

import com.soft.apilab.repositories.ReserveRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@EnableAsync
@Component
public class ReserveCollector {

    final ReserveRepository reserveRepository;

    public ReserveCollector(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    @Transactional
    @Async
    @Scheduled(fixedRate = 3600000)
    public void verifyReserveStatus() throws InterruptedException {
        reserveRepository.updateToConcludedReservesStatus(LocalDateTime.now());
    }
}
