package cl.sentra.testbci.repository;

import cl.sentra.testbci.repository.model.PhoneEntity;
import cl.sentra.testbci.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);

    List<UserEntity> findAll();

}
