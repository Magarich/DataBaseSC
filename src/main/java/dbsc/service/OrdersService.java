package dbsc.service;

import dbsc.convecter.OrderConverter;
import dbsc.convecter.PriceOfWorkConverter;
import dbsc.dto.InfoOrderDto;
import dbsc.dto.PriceOfWorkDto;
import dbsc.entity.OrderEntity;
import dbsc.entity.PriceOfWorkEntity;
import dbsc.repository.ClientRepository;
import dbsc.repository.OrderRepository;
import dbsc.convecter.ClientConverter;
import dbsc.dto.CreateOrderDto;
import dbsc.dto.OrderDto;
import dbsc.exception.HttpException;
import dbsc.entity.enums.OrderStatus;
import dbsc.repository.PriceOfWorkRepository;
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
    private final PriceOfWorkRepository priceOfWorkRepository;


    public OrderEntity create(CreateOrderDto orderDto){
        Integer id = orderDto.getClientId();

        if(!clientRepository.existsById(id))
            throw new HttpException(String.format(
                    "Impossible create order, because client with id = %s doesn't exist!", id), HttpStatus.NOT_FOUND);

        return ordersRepository.save(OrderEntity.builder()
                .typeDevice(orderDto.getTypeDevice())
                .nameDevice(orderDto.getNameDevice())
                .status(OrderStatus.NEW.getTitle())
                .malfunction(orderDto.getMalfunction())
                .date(new Date())
                .note(orderDto.getNote())
                .clientEntity(clientRepository.getOne(id))
                .build());
    }

    public List<OrderDto> getOrders(Integer orderId, Integer clientId, String orderStatus){
        List<OrderDto> orderDtos = new ArrayList<>();
        if(orderId != null){
            Optional<OrderEntity> optionalOrderEntity = ordersRepository.findById(orderId);
            if (!optionalOrderEntity.isPresent()) {
                throw new HttpException(String.format("Order with id = %s doesn't exist!", orderId), HttpStatus.NOT_FOUND);
            }
            orderDtos.add(OrderConverter.toOrderDto(optionalOrderEntity.get()));
        }
        else if(clientId != null){

        }
        else {
            orderDtos.addAll(OrderConverter.toOrderDto(ordersRepository.findAll()));
        }
        return  orderDtos;

    }


    public List<OrderDto> update(Integer orderId, CreateOrderDto createOrdersDto){
        Optional<OrderEntity> opt = ordersRepository.findById(orderId);
        if (!opt.isPresent()) {
            throw new HttpException(String.format("Order with id = %s doesn't exist!", orderId), HttpStatus.NOT_FOUND);
        }

        return  null;
    }


    public PriceOfWorkEntity addTypeWork(Integer id, PriceOfWorkDto priceOfWorkDto) {
        Optional<OrderEntity> opt = ordersRepository.findById(69);
        if (!opt.isPresent()) {
            throw new HttpException(String.format("Order with id = %s doesn't exist!", id), HttpStatus.NOT_FOUND);
        }
        PriceOfWorkEntity priceOfWorkEntity = PriceOfWorkConverter.toPriceOfWorkEntity(priceOfWorkDto);
        priceOfWorkEntity.setDateAdd(new Date());
        priceOfWorkEntity.setOrder(ordersRepository.getById(id));
        return priceOfWorkRepository.save(priceOfWorkEntity);
    }

    public InfoOrderDto orderInfo(Integer id) {
        OrderEntity orderEntity = ordersRepository.getById(id);
        return InfoOrderDto.builder()
                .id(orderEntity.getId())
                .nameDevice(orderEntity.getNameDevice())
                .typeDevice(orderEntity.getTypeDevice())
                .dateCreate(orderEntity.getDate())
                .status(orderEntity.getStatus())
                .note(orderEntity.getNote())
                .totalCost(0d)
                .malfunction(orderEntity.getMalfunction())
                .client(ClientConverter.toClientDto(orderEntity.getClientEntity()))
                .priceOfWorkDtoList(PriceOfWorkConverter.toPriceOfWorkDto(orderEntity.getPriceOfWorkEntityList()))
                .build();
    }
}
