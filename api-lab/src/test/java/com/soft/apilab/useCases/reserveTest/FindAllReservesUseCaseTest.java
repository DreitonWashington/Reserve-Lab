package com.soft.apilab.useCases.reserveTest;

import com.soft.apilab.dtos.InstructorDto;
import com.soft.apilab.dtos.LabDto;
import com.soft.apilab.dtos.ReserveDto;
import com.soft.apilab.dtos.SubjectDto;
import com.soft.apilab.models.Lab;
import com.soft.apilab.models.Reserve;
import com.soft.apilab.models.Subject;
import com.soft.apilab.useCases.instructor.CreateInstructorUseCase;
import com.soft.apilab.useCases.lab.CreateLabUseCase;
import com.soft.apilab.useCases.reserve.CreateReserveUseCase;
import com.soft.apilab.useCases.reserve.FindAllReservesUseCase;
import com.soft.apilab.useCases.subject.CreateSubjectUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
public class FindAllReservesUseCaseTest {

    @Autowired
    CreateReserveUseCase createReserveUseCase;
    @Autowired
    CreateInstructorUseCase createInstructorUseCase;
    @Autowired
    CreateLabUseCase createLabUseCase;
    @Autowired
    CreateSubjectUseCase createSubjectUseCase;

    @Autowired
    FindAllReservesUseCase findAllReservesUseCase;

    @BeforeEach
    void createReserves(){
        this.createDataToTest();
    }

    @Test
    void findAllReservesUseCaseFailBecauseReservesIsEmptyTest(){
        Pageable page = PageRequest.of(0,5);
        Page<Reserve> reserves = findAllReservesUseCase.findAllReserves(page);
        Assertions.assertTrue(reserves.getContent().isEmpty());
    }

    @Test
    void findAllReservesUseCaseSuccessTest(){
        Pageable page = PageRequest.of(0,5);
        Page<Reserve> reserves = findAllReservesUseCase.findAllReserves(page);
        Assertions.assertTrue(!reserves.getContent().isEmpty());
    }

    public void createDataToTest(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("DB MYSQL");

        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setInstructorId("g2eby");
        instructorDto.setName("Julio Cesar");

        LabDto labDto = new LabDto();
        labDto.setBlock("A".charAt(0));
        labDto.setCapacity(20);
        labDto.setName("Lab Info 11");

        Subject subject = createSubjectUseCase.createNewSubject(subjectDto);
        Lab lab = createLabUseCase.createNewLab(labDto);
        createInstructorUseCase.createNewInstructor(instructorDto);

        ReserveDto reserve = new ReserveDto();
        reserve.setInstructorId("g2eby");
        reserve.setLabId(lab.getLabId());
        reserve.setSubjectId(subject.getSubjectId());
        reserve.setStartTime(LocalDateTime.now());
        reserve.setEndTime(LocalDateTime.now().plusDays(1));

        ReserveDto reserve2 = new ReserveDto();
        reserve2.setInstructorId("g2eby");
        reserve2.setLabId(lab.getLabId());
        reserve2.setSubjectId(subject.getSubjectId());
        reserve2.setStartTime(LocalDateTime.now().plusDays(2));
        reserve2.setEndTime(LocalDateTime.now().plusDays(3));

        createReserveUseCase.createNewReserve(reserve);
        createReserveUseCase.createNewReserve(reserve2);

    }
}
