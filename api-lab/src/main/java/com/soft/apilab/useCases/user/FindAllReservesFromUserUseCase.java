package com.soft.apilab.useCases.user;

import com.soft.apilab.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindAllReservesFromUserUseCase {

    final UserRepository userRepository;

    public FindAllReservesFromUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*public Page<Reserve> findAllReservesFromUser(String userId){
        return this.userRepository.findAllReservesFromUser(userId);
    }*/
}
