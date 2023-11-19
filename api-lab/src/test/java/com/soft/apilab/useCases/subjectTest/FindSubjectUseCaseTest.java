package com.soft.apilab.useCases.subjectTest;

import com.soft.apilab.dtos.SubjectDto;
import com.soft.apilab.useCases.subject.CreateSubjectUseCase;
import com.soft.apilab.useCases.subject.FindSubjectUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
public class FindSubjectUseCaseTest {

    @Autowired
    FindSubjectUseCase findSubjectUseCase;
    @Autowired
    CreateSubjectUseCase createSubjectUseCase;

    private UUID subjectIdTest;

    @BeforeEach
    void createDataToTest(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("MYSQL COURSE");

        this.subjectIdTest = createSubjectUseCase.createNewSubject(subjectDto).getSubjectId();
    }

    @Test
    void findSubjectUseCaseSuccessTest(){
        Assertions.assertEquals("MYSQL COURSE", findSubjectUseCase.findSubject(this.subjectIdTest).getName());
    }
}
