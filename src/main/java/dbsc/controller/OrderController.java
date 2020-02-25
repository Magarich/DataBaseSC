package dbsc.controller;

import dbsc.dto.CreateOrderDto;
import dbsc.dto.InfoOrderDto;
import dbsc.dto.OrderDto;
import dbsc.dto.PriceOfWorkDto;
import dbsc.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService ordersService;
    @GetMapping("/{id}")
    public InfoOrderDto order(@PathVariable Integer id){
        return ordersService.orderInfo(id);
    }


    @PostMapping("/create")//добавить, изменить, удалить
    public void create(@RequestBody CreateOrderDto createOrderDto) {
        ordersService.create(createOrderDto);
    }

    @GetMapping("/get")
    public List<OrderDto> getOrders(@RequestParam(name = "orderId", required = false) Integer orderId,
                                    @RequestParam(name = "clientId", required = false) Integer clientId,
                                    @RequestParam(name = "orderStatus", required = false) String orderStatus) {
        return ordersService.getOrders(orderId, clientId, orderStatus);
    }

    @PutMapping("/{id}/update")//добавить, заменить
    public void update(@PathVariable Integer id, @RequestBody CreateOrderDto ordersDto) {
        ordersService.update(id,ordersDto);
    }


    /*
    {
	"kindOfWork":"Профилактика",
	"price": 400
}
    */
    @PutMapping("/{id}/addTypeWork")
    public void addTypeWork(@PathVariable Integer id, @RequestBody PriceOfWorkDto priceOfWorkDto){
        ordersService.addTypeWork(id, priceOfWorkDto);
    }


}
