package com.soft.apilab.controllers;

import com.soft.apilab.dtos.LabDto;
import com.soft.apilab.dtos.LoginDto;
import com.soft.apilab.dtos.UserDto;
import com.soft.apilab.useCases.lab.CreateLabUseCase;
import com.soft.apilab.useCases.user.CreateUserUseCase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LabControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CreateLabUseCase createLabUseCase;
    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private AuthenticationController authenticationController;


    private String token;
    @BeforeEach
    void setup(){
        UserDto userDto = new UserDto("Maria", "maria", "maria@gmail.com", "123456", UserDto.gerarInstructorId(5));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        var user = createUserUseCase.createNewUser(userDto);
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(user.getUsername());
        loginDto.setPassword("123456");

        this.token = authenticationController.authenticateUser(loginDto).getBody().getToken();
    }

    @Test
    void labControllerCreateLabTest() throws Exception {
        LabDto labDto = new LabDto();
        labDto.setName("Lab Info 2");
        labDto.setCapacity(10);
        labDto.setBlock("A".charAt(0));

        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.token)
                .body(labDto)
                .when().post("labs/create-lab")
                .then().statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("name", equalTo(labDto.getName()));
    }

    @Test
    void labControllerCreateLabFailEmptyNameTest(){
        LabDto labDto = new LabDto();
        labDto.setName("");
        labDto.setCapacity(10);
        labDto.setBlock("A".charAt(0));


        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.token)
                .body(labDto)
                .when().post("labs/create-lab")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
