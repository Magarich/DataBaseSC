package dbsc.controller;

import dbsc.dto.ClientDto;
import dbsc.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public void create(@RequestBody ClientDto clientDto){
        clientService.create(clientDto);
    }


    //TODO сделать универсальный метод который будет возвращать список ДТО(id, all, Params)
    @GetMapping("/get")
    public List<ClientDto> get(@RequestParam(name = "id", required = false) Integer id,
                               @RequestParam(name = "firstName", required = false) String firstName,
                               @RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "phoneNumber", required = false) String phoneNumber){

        return clientService.get(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") Integer id){
        clientService.delete(id);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        clientService.update(id, clientDto);
    }


    //смотреть информацию о заказе(ид, устройство, тип, дата создание, статус, стоипость, проведённые работы)
    //оплачивать, отменять ремонт
    //оставлять свой коментарий
}
