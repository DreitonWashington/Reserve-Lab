package com.soft.apilab.useCases.lab;

import com.soft.apilab.models.Lab;
import com.soft.apilab.repositories.LabRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindLabUseCase {

    final LabRepository labRepository;

    public FindLabUseCase(LabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public Lab findLab(UUID labId){
        return labRepository.findById(labId).orElseThrow( () -> new RuntimeException("Lab Not Found!"));
    }
}
