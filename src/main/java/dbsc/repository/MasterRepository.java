package dbsc.repository;

import dbsc.entity.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<MasterEntity, Integer> {
}
