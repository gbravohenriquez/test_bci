package cl.sentra.testbci.controller;

import cl.sentra.testbci.dto.UserDTO;
import cl.sentra.testbci.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void createUser_ok() {
        ResponseEntity<UserDTO> response;
        LocalDateTime localDateTime = LocalDateTime.now();
        UserDTO userDTO =  UserDTO.builder().
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                build();

        UserDTO userRequest = new UserDTO().builder().
                id("1L").
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                modified(localDateTime).
                created(localDateTime).
                lastLogin(localDateTime).
                isActive(true).
                build();

        when(userService.createUser(userDTO)).thenReturn(userRequest);
        response = userController.createUser(userDTO);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);

    }

    public void update() {
    }

    public void delete() {
    }

    public void testUser() {
    }
}