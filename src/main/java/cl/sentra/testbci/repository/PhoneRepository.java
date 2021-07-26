package cl.sentra.testbci.repository;

import cl.sentra.testbci.repository.model.PhoneEntity;
import cl.sentra.testbci.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends JpaRepository<PhoneEntity, UUID> {
    public List<PhoneEntity> findAllByUser(UserEntity userId);
}
