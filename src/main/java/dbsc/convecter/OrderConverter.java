package dbsc.convecter;

import dbsc.entity.OrderEntity;
import dbsc.dto.CreateOrderDto;
import dbsc.dto.OrderDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderDto toOrderDto (OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .nameDevice(orderEntity.getNameDevice())
                .typeDevice(orderEntity.getTypeDevice())
                .status(orderEntity.getStatus())
                .date(orderEntity.getDate())
                .malfunction(orderEntity.getMalfunction())
                .note(orderEntity.getNote())
                .price(0d)
                .build();
    }

    public static List<OrderDto> toOrderDto(List<OrderEntity> orderEntitiesList){
        List<OrderDto> orderDtoList = orderEntitiesList.stream()
                .map(OrderConverter::toOrderDto)
                .collect(Collectors.toList());
        return orderDtoList;
    }

  //  public static List<ClientDto> toClientDto(List<ClientEntity> clientEntityList){
 //       List<ClientDto> clientDtoList = clientEntityList.stream()
 //               .map(ClientConverter::toClientDto)
 //               .collect(Collectors.toList());
 //       return clientDtoList;

 //   }
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
