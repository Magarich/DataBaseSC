package dbsc.repository;

import dbsc.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    ClientEntity findByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);

}
