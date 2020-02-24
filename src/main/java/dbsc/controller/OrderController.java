package dbsc.controller;

import dbsc.dto.CreateOrderDto;
import dbsc.dto.OrderDto;
import dbsc.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService ordersService;

    @PostMapping("/c")//добавить, изменить, удалить
    public void create(@RequestBody CreateOrderDto createOrderDto) {
        ordersService.create(createOrderDto);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(name = "orderId", required = false) Integer orderId,
                                    @RequestParam(name = "clientId", required = false) Integer clientId,
                                    @RequestParam(name = "orderStatus", required = false) String orderStatus) {
        return ordersService.getOrders(orderId, clientId, orderStatus);
    }

    @PutMapping("/{id}/update")//добавить, заменить
    public void update(@PathVariable Integer id, @RequestBody CreateOrderDto ordersDto) {
        ordersService.update(id,ordersDto);
    }


}
