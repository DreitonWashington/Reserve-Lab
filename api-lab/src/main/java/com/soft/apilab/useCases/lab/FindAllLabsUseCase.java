package com.soft.apilab.useCases.lab;

import com.soft.apilab.models.Lab;
import com.soft.apilab.repositories.LabRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FindAllLabsUseCase {

    final LabRepository labRepository;

    public FindAllLabsUseCase(LabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public Page<Lab> findAllLabs(Specification<Lab> spec, Pageable pageable){
        return labRepository.findAll(spec, pageable);
    }

}
