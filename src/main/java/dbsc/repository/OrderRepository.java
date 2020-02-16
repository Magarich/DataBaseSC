package dbsc.repository;

import dbsc.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity getById(int id);
    List<OrderEntity> getAllByIdOrClientEntityOrStatus(Integer orderId, Integer clientId, String orderStatus);
}
