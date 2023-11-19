package com.soft.apilab.useCases.subjectTest;

import com.soft.apilab.dtos.SubjectDto;
import com.soft.apilab.useCases.subject.CreateSubjectUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class CreateSubjectUseCaseTest {

    @Autowired
    CreateSubjectUseCase createSubjectUseCase;

    @Test
    void createNewSubjectSuccessTest(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("DB MYSQL");

        var subject = createSubjectUseCase.createNewSubject(subjectDto);
        Assertions.assertEquals(subjectDto.getName(), subject.getName());
    }

    @Test
    void createNewSubjectFailTest(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var subject = createSubjectUseCase.createNewSubject(subjectDto);
        });
    }
}
