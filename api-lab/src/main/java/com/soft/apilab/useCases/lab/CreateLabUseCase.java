package com.soft.apilab.useCases.lab;

import com.soft.apilab.dtos.LabDto;
import com.soft.apilab.models.Lab;
import com.soft.apilab.repositories.LabRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateLabUseCase {

    final LabRepository labRepository;

    public CreateLabUseCase(LabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public Lab createNewLab(LabDto labDto){
        Lab lab = new Lab();

        /*if(labDto.getName().isEmpty()){
            throw new IllegalArgumentException("Lab name cannot be empty");
        }*/
        lab.setName(labDto.getName());
        lab.setBlock(labDto.getBlock());
        lab.setCapacity(labDto.getCapacity());
        return labRepository.save(lab);
    }
}
