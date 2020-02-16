package dbsc.service;

import dbsc.entity.ClientEntity;
import dbsc.entity.OrderEntity;
import dbsc.repository.ClientRepository;
import dbsc.repository.OrderRepository;
import dbsc.convecter.ClientConverter;
import dbsc.dto.CreateOrderDto;
import dbsc.dto.OrderDto;
import dbsc.exception.HttpException;
import dbsc.entity.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository ordersRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;



    public OrderEntity createOrder(CreateOrderDto createOrdersDto){
        ClientEntity clientEntity = clientRepository.findByFirstNameAndLastNameAndPhoneNumber(
                createOrdersDto.getFirstName(),
                createOrdersDto.getLastName(),
                createOrdersDto.getPhoneNumber());

         if(clientEntity == null) {
            clientEntity = clientService.create(ClientConverter.toClientDto(createOrdersDto));
        }

         return ordersRepository.save(OrderEntity.builder()
                 .typeDevice(createOrdersDto.getTypeDevice())
                 .nameDevice(createOrdersDto.getNameDevice())
                 .status(OrderStatus.NEW.getTitle())
                 .malfunction(createOrdersDto.getMalfunction())
                 .date(new Date())
                 .note(createOrdersDto.getNote())
                 .clientEntity(clientEntity)
         .build());
    }

    public List<OrderDto> getOrders(Integer orderId, Integer clientId, String orderStatus){
        List<OrderDto> orderDtos = new ArrayList<>();

        return  orderDtos;

    }


    public List<OrderDto> update(Integer orderId, CreateOrderDto createOrdersDto){
        Optional<ClientEntity> opt = clientRepository.findById(orderId);
        if (!opt.isPresent()) {
            throw new HttpException(String.format("Order with id = %s doesn't exist!", orderId), HttpStatus.NOT_FOUND);
        }

        return  null;
    }


}
