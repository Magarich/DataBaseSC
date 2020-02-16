package dbsc.service;


import dbsc.convecter.MasterConverter;
import dbsc.dto.MasterDto;
import dbsc.entity.MasterEntity;
import dbsc.exception.HttpException;
import dbsc.repository.MasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;


    public void createMaster(MasterDto masterDto) {
        //TODO проверить есть ли такая сущность в базе
        masterRepository.save(MasterConverter.toMasterEntity(masterDto));
    }


    public void delete(Integer id) {
        masterRepository.deleteById(id);
    }

    public List<MasterDto> get(Integer id){
        Optional<MasterEntity> opt;
        List<MasterDto> masterDtos;
        if(id == null){
            masterDtos = MasterConverter.toMasterDtos(masterRepository.findAll());
        }
        else {
            opt  = masterRepository.findById(id);
            if(!opt.isPresent()){
                throw new HttpException(String.format("Master with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
            }
            masterDtos = Arrays.asList(MasterConverter.toMasterDto(masterRepository.getOne(id)));
        }
        return masterDtos;

    }

    public void update(Integer id, MasterDto masterDto) {
        Optional<MasterEntity> optId = masterRepository.findById(id);
        if (!optId.isPresent()) {
            throw new HttpException(String.format("Master with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
        }
        masterRepository.save(MasterConverter.update(optId.get(),masterDto));
    }
}
