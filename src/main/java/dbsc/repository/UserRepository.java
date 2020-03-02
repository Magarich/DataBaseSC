package dbsc.repository;

import dbsc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    UserEntity findByUsername(String name);
}
