package com.soft.apilab.useCases.labTest;

import com.soft.apilab.dtos.LabDto;
import com.soft.apilab.models.Lab;
import com.soft.apilab.useCases.lab.CreateLabUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class CreateNewLabUseCaseTest {

    @Autowired
    CreateLabUseCase createLabUseCase;

    @Test
    void createNewLabUseCaseSuccessTest(){
        LabDto labDto = new LabDto();
        labDto.setName("Lab Info 2");
        labDto.setBlock("A".charAt(0));
        labDto.setCapacity(10);

        Lab lab = createLabUseCase.createNewLab(labDto);
        Assertions.assertEquals(labDto.getName(), lab.getName());
        Assertions.assertEquals(labDto.getBlock(), lab.getBlock());
    }

    @Test
    void createNewLabUseCaseFailNameEmptyTest(){
        LabDto labDto = new LabDto();
        labDto.setName("");
        labDto.setBlock("A".charAt(0));
        labDto.setCapacity(10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           createLabUseCase.createNewLab(labDto);
        });
    }
}
