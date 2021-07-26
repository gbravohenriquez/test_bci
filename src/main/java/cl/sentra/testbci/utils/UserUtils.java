package cl.sentra.testbci.utils;

import cl.sentra.testbci.dto.PhoneDTO;
import cl.sentra.testbci.dto.UserDTO;
import cl.sentra.testbci.repository.model.PhoneEntity;
import cl.sentra.testbci.repository.model.UserEntity;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class UserUtils {

    public static UserEntity createUserDtoToUserEntity(UserDTO user, String token) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return UserEntity.builder().name(user.getName()).
                email(user.getEmail()).
                password(user.getPassword()).
                phones(user.
                        getPhones().
                        stream().
                        map(phoneRequestDTO -> {
                            return PhoneEntity.builder().
                                    citycode(phoneRequestDTO.getCitycode()).
                                    countrycode(phoneRequestDTO.getCountrycode()).
                                    number(phoneRequestDTO.getNumber()).build();
                        }).collect(Collectors.toList())).
                created(localDateTime).
                modified(localDateTime).token(token).
                lastLogin(localDateTime).password(user.getPassword()).
                isActive(true).
                build();
    }

    public static UserDTO userEntityToUserDto(UserEntity user) {
        return UserDTO.builder().name(user.getName()).
                email(user.getEmail()).
                password(user.getPassword()).
                phones(user.
                        getPhones().
                        stream().
                        map(phoneEntity -> {
                            return PhoneDTO.builder().
                                    citycode(phoneEntity.getCitycode()).
                                    countrycode(phoneEntity.getCountrycode()).
                                    number(phoneEntity.getNumber()).build();
                        }).collect(Collectors.toList())).
                created(user.getCreated()).
                modified(user.getModified()).token(user.getToken()).
                lastLogin(user.getLastLogin()).password(user.getPassword()).
                isActive(true).
                build();
    }
}
