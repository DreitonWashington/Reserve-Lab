package com.soft.apilab.useCases.user;

import com.soft.apilab.models.User;
import com.soft.apilab.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllUsersUseCase {

    final UserRepository userRepository;

    public FindAllUsersUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Page<User> findAllUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }
}
