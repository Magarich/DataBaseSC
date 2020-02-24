package dbsc.service;


import dbsc.entity.ClientEntity;
import dbsc.exception.HttpException;
import dbsc.repository.ClientRepository;
import dbsc.convecter.ClientConverter;
import dbsc.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
//при создании Сущности, всегда возвращать DTO???

    public ClientEntity create(ClientDto clientDto){
        Optional<ClientEntity> opt = Optional.ofNullable(clientRepository
                .findByFirstNameAndLastNameAndPhoneNumber(clientDto.getFirstName()
                        ,clientDto.getLastName()
                        ,clientDto.getPhoneNumber()));
        //Проверка на наличии существующей сущности в базе
        if(opt.isPresent()){
            throw new HttpException(String.format("Client with" +
                    " firstName = %s," +
                    " lastName = %s," +
                    " phoneNumber = %s" +
                    " is exist!"
                    ,clientDto.getFirstName()
                    ,clientDto.getLastName()
                    ,clientDto.getPhoneNumber()), HttpStatus.BAD_REQUEST);
        }
        ClientEntity clientEntity = ClientConverter.toClientEntity(clientDto);
        ClientEntity save = clientRepository.save(clientEntity);
        return save;
    }

    public List<ClientDto> get(Integer id){

        List<ClientEntity> clientEntityList = new ArrayList<>();


        if(id != null){
            Optional<ClientEntity> opt = clientRepository.findById(id);
            if (!opt.isPresent()) {
                throw new HttpException(String.format("Client with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
            }
            clientEntityList.add(opt.get());
        }
        else{
            clientEntityList.addAll(clientRepository.findAll());
        }
        return ClientConverter.toClientDto(clientEntityList);
    }

    public void delete(Integer id) {
        Optional<ClientEntity> opt = clientRepository.findById(id);
        if (!opt.isPresent()) {
            throw new HttpException(String.format("Client with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
        }
            clientRepository.deleteById(id);
    }

    public void update(Integer id, ClientDto clientDto) {
        Optional<ClientEntity> opt = clientRepository.findById(id);
        if (!opt.isPresent()) {
            throw new HttpException(String.format("Client with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
        }
        clientRepository.save(ClientConverter.update(opt.get(),clientDto));
    }
}
