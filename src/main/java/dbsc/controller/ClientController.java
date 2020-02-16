package dbsc.controller;

import dbsc.dto.ClientDto;
import dbsc.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    //что лучше, принимать ПАРАМЕТРЫ или JSON?????

    //TODO сделать универсальный метод который будет возвращать список ДТО(id, all, Params)
    @GetMapping("/get")
    public ClientDto getClient(@RequestParam(name = "id", required = false) Integer id,
                               @RequestParam(name = "firstName", required = false) String firstName,
                               @RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "phoneNumber", required = false) String phoneNumber){
        Optional<Integer> opt = Optional.ofNullable(id);
        if(opt.isPresent())
            return clientService.getClient(opt.get());

        return clientService.getClient(firstName, lastName, phoneNumber);
    }

    @GetMapping("/ggg")
    public List<ClientDto> get(@RequestParam(name = "id", required = false) Integer id,
                               @RequestParam(name = "firstName", required = false) String firstName,
                               @RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "phoneNumber", required = false) String phoneNumber){
        Optional<Integer> optId = Optional.ofNullable(id);
        Optional<String> optFirstName = Optional.ofNullable(firstName);
        Optional<String> optLastName = Optional.ofNullable(lastName);
        Optional<String> optPhoneNumber = Optional.ofNullable(phoneNumber);
        List<Optional> list = Arrays.asList(optId,optFirstName,optLastName,optPhoneNumber);

        return clientService.get(list);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") Integer id){
        clientService.delete(id);
    }

    //Где лучше обворачивать в Optional? в контроллере или в сервисе?
    @PutMapping("/{id}/update")
    public void update(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        clientService.update(id, clientDto);
    }


    //смотреть информацию о заказе(ид, устройство, тип, дата создание, статус, стоипость, проведённые работы)
    //оплачивать, отменять ремонт
    //оставлять свой коментарий
}
