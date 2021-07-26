package cl.sentra.testbci.service;

import cl.sentra.testbci.dto.PhoneDTO;
import cl.sentra.testbci.dto.UserDTO;
import cl.sentra.testbci.exception.ControlException;
import cl.sentra.testbci.repository.UserRepository;
import cl.sentra.testbci.repository.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    private List<UserEntity> userEntities;

    @Test
    public void get_all_users_no_ok() {
        when(repository.findAll()).thenReturn(this.userEntities);
        assertThrows(ControlException.class, () -> {
            userService.getall();
        });
    }

    @Test
    public void create_user_no_ok() {
        UserEntity userEntity = UserEntity.builder().
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                build();

        PhoneDTO phoneDTO = PhoneDTO.builder().
                citycode("1").countrycode("2").number("3").build();
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        phoneDTOList.add(phoneDTO);
        UserDTO userDTO = UserDTO.builder().
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                build();

        when(repository.findByEmail("guillermo@guillermo.cl")).thenReturn(userEntity);
        assertThrows(ControlException.class, () -> {
            userService.createUser(userDTO);
        });
    }

    @Test
    public void create_user_ok() {
        UserEntity userEntity2 = UserEntity.builder().
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                build();

        PhoneDTO phoneDTO = PhoneDTO.builder().
                citycode("1").countrycode("2").number("3").build();
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        phoneDTOList.add(phoneDTO);
        UserDTO userDTO = UserDTO.builder().
                name("guillermo").
                email("guillermo@guillermo.cl").
                password("password").
                phones(new ArrayList<>()).
                build();

        when(repository.findByEmail("guillermo@guillermo.cl")).thenReturn(null);
        when(repository.save(any())).thenReturn(userEntity2);
        UserDTO response = userService.createUser(userDTO);
        assertTrue(response.getEmail().equalsIgnoreCase(userDTO.getEmail()));
        assertTrue(response.getName().equalsIgnoreCase(userDTO.getName()));
    }
}