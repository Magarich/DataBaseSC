package dbsc.controller;

import dbsc.dto.MasterDto;
import dbsc.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {

    private final MasterService masterService;

    @PostMapping("/create")
    public void create(@RequestBody MasterDto masterDto){
        masterService.createMaster(masterDto);
    }

    @GetMapping("/get")
    public List<MasterDto> get(@RequestParam(name = "id", required = false) Integer id){
        return masterService.get(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "id") Integer id){
        masterService.delete(id);
    }

    @PutMapping("/{id}/update")
    public void update(@PathVariable Integer id, @RequestBody MasterDto masterDto){
        masterService.update(id, masterDto);
    }

    //прописывать коментарий в заказе
    //Менять статус
    //выставлять цену(если "ОПЛАЧЕНО"!= False)
}
