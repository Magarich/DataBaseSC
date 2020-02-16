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
//что лучше, принимать ПАРАМЕТРЫ или JSON?????
    public ClientDto getClient(String firstName, String lastName, String phoneNumber){
        Optional<ClientEntity> opt = Optional.ofNullable(clientRepository
                .findByFirstNameAndLastNameAndPhoneNumber(firstName, lastName, phoneNumber));//А если зайдёт Null, насколько геморно его обворачивать в Optional
        if(!opt.isPresent()){
            throw new HttpException(String.format("Client with" +
                    " firstName = %s," +
                    " lastName = %s," +
                    " phoneNumber = %s" +
                    " doesn't exist!",firstName, lastName, phoneNumber), HttpStatus.BAD_REQUEST);
        }
        return ClientConverter.toClientDto(opt.get());
    }

    public ClientDto getClient(Integer id){
        Optional<ClientEntity> opt = clientRepository.findById(id);
        if(!opt.isPresent()){
            throw new HttpException(String.format("Client with id = %s doesn't exist!", id), HttpStatus.BAD_REQUEST);
        }
        return ClientConverter.toClientDto(opt.get());
    }

    public List<ClientDto> get(List<Optional> optionals){
        System.out.println(optionals.get(0).isPresent());
        List<ClientEntity> clientEntityList = null;
        if(optionals.get(0).isPresent()){
            clientEntityList.add(clientRepository.getOne((Integer) optionals.get(0).get()));
        }
        else if(!optionals.get(1).isPresent() || !optionals.get(2).isPresent() || !optionals.get(3).isPresent()){

        }
        else{
            clientEntityList = clientRepository.findAll();
        }


        List<ClientDto> clientDtoList = ClientConverter.toClientDto(clientEntityList);
        return clientDtoList;
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
