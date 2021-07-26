package cl.sentra.testbci.controller;

import cl.sentra.testbci.dto.UserDTO;
import cl.sentra.testbci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> delete(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.deleteUser(userDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> allUsers() {
        return ResponseEntity.ok(userService.getall());
    }

}
