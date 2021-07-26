package cl.sentra.testbci.service;

import cl.sentra.testbci.dto.PhoneDTO;
import cl.sentra.testbci.dto.UserDTO;
import cl.sentra.testbci.exception.ControlException;
import cl.sentra.testbci.repository.UserRepository;
import cl.sentra.testbci.repository.model.UserEntity;
import cl.sentra.testbci.security.JwtTokenUtil;
import cl.sentra.testbci.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static cl.sentra.testbci.enums.UserError.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userRequest) {
        UserEntity user;
        validaEmail(userRequest.getEmail());

        String token = JwtTokenUtil.getJWTToken(userRequest.getName());
        UserEntity userEntity = UserUtils.createUserDtoToUserEntity(userRequest, token);

        user = userRepository.save(userEntity);

        return UserUtils.userEntityToUserDto(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userRequest) {
        UserEntity userEntity = validaUser(userRequest.getEmail(), userRequest.getPassword());
        userRepository.delete(userEntity);
        UserEntity user = UserUtils.createUserDtoToUserEntity(userRequest, userEntity.getToken());
        user.setCreated(userEntity.getCreated());

        return UserUtils.userEntityToUserDto(userRepository.save(user));
    }

    @Override
    public UserDTO deleteUser(UserDTO userRequest) {
        validaUser(userRequest.getEmail(), userRequest.getPassword());

        UserEntity userEntity = userRepository.findByEmail(userRequest.getEmail());
        userRepository.delete(userEntity);

        return new UserDTO();
    }

    @Override
    public List<UserDTO> getall() {
        List<UserEntity> userEntities = userRepository.findAll();
        if(userEntities == null){
            throw new ControlException(E_SIN_REGISTROS.getDescripcion());
        }
        List<UserDTO> userDTOS = userEntities.stream()
                .map(userEntity -> {
                    return UserDTO.builder().id(userEntity.getId()).name(userEntity.getName()).
                            email(userEntity.getEmail()).
                            password(userEntity.getPassword()).
                            phones(userEntity.
                                    getPhones().
                                    stream().
                                    map(phoneEntity -> {
                                        return PhoneDTO.builder().
                                                citycode(phoneEntity.getCitycode()).
                                                countrycode(phoneEntity.getCountrycode()).
                                                number(phoneEntity.getNumber()).build();
                                    }).collect(Collectors.toList())).
                            created(userEntity.getCreated()).
                            modified(userEntity.getModified()).token(userEntity.getToken()).
                            lastLogin(userEntity.getLastLogin()).password(userEntity.getPassword()).
                            isActive(true).
                            build();
                }).collect(Collectors.toList());

        return userDTOS;
    }

    private void validaEmail(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new ControlException(E_EMAIL_REGISTRADO.getDescripcion());
        }
    }

    private UserEntity validaUser(String email, String password) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(email, password);
        if (userEntity == null) {
            throw new ControlException(E_SIN_REGISTROS_ACTUALIZAR.getDescripcion());
        }
        return userEntity;
    }

}
