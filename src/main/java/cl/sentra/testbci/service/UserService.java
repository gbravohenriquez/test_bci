package cl.sentra.testbci.service;

import cl.sentra.testbci.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser (UserDTO userRequest);

    UserDTO updateUser (UserDTO userRequest);

    UserDTO deleteUser(UserDTO userRequest);

    List<UserDTO> getall();
}
