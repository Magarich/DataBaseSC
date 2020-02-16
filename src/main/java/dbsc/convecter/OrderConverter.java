package dbsc.convecter;

import dbsc.entity.OrderEntity;
import dbsc.dto.CreateOrderDto;
import dbsc.dto.OrderDto;

import java.util.Date;

public class OrderConverter {
    public static OrderDto toOrderDto (OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .nameDevice(orderEntity.getNameDevice())
                .typeDevice(orderEntity.getTypeDevice())
                .status(orderEntity.getStatus())
                .date(orderEntity.getDate())
                .build();
    }
    public static OrderEntity toOrderEntity (CreateOrderDto createOrderDto){
        return OrderEntity.builder()
                .note(createOrderDto.getNote())
                .malfunction(createOrderDto.getMalfunction())
                .nameDevice(createOrderDto.getNameDevice())
                .typeDevice(createOrderDto.getTypeDevice())
                .date(new Date())
                .status("NEW")
                .build();
    }
}
