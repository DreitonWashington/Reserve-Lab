package com.soft.apilab.useCases.userTest;

import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.models.User;
import com.soft.apilab.useCases.user.CreateUserUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.TransactionSystemException;

@ActiveProfiles("test")
@SpringBootTest
public class CreateUserUseCaseTest {

    @Autowired
    CreateUserUseCase createUserUseCase;

    @Test
    void createNewUserUseCaseSuccessTest(){
        UserDto userDto = new UserDto("Paulo Lima","paulo", "paulo@gmail.com",
                "123456", UserDto.gerarInstructorId(5));

        User user = createUserUseCase.createNewUser(userDto);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(userDto.getName(), user.getInstructor().getName());
    }

    @Test
    void createNewUserUseCaseFailUsernameExistsTest(){
        UserDto userDto = new UserDto("Paulo Lima","paulo", "paulo@gmail.com",
                "123456", UserDto.gerarInstructorId(5));

        User user = createUserUseCase.createNewUser(userDto);
        Assertions.assertThrows(
                DataIntegrityViolationException.class, ()->{
                    User user2 = createUserUseCase.createNewUser(new UserDto("Julia Maria", "paulo",
                            "juliamaria@gmail.com", "123456", UserDto.gerarInstructorId(5)));
                }
        );
    }

    @Test
    void createNewUserUseCaseFailEmptyUsernameTest(){
        UserDto userDto = new UserDto("Lucas Silva","", "lucas@gmail.com",
                "123456", UserDto.gerarInstructorId(5));
        Assertions.assertThrows(
                TransactionSystemException.class, ()->{
                    User user = createUserUseCase.createNewUser(userDto);
                });
    }

    @Test
    void createNewUserUseCaseFailEmptyPasswordTest(){
        UserDto userDto = new UserDto("Lucas Silva","lucas", "lucas@gmail.com",
                "", UserDto.gerarInstructorId(5));
        Assertions.assertThrows(
                TransactionSystemException.class, ()->{
                    User user = createUserUseCase.createNewUser(userDto);
                });
    }
}
