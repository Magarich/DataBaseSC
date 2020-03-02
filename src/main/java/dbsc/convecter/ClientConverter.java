package dbsc.convecter;

import dbsc.entity.ClientEntity;
import dbsc.dto.ClientDto;
import dbsc.dto.CreateOrderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {
    //TODO read about modelmapper (org.modelmapper)

    public static ClientEntity toClientEntity(ClientDto clientDto) {
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .phoneNumber(clientDto.getPhoneNumber())
                //.orderEntities(new ArrayList<>())
                .build();
    }

    public static ClientDto toClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .phoneNumber(clientEntity.getPhoneNumber())
                .build();
    }

    public static List<ClientDto> toClientDto(List<ClientEntity> clientEntityList){
        List<ClientDto> clientDtoList = clientEntityList.stream()
                .map(ClientConverter::toClientDto)
                .collect(Collectors.toList());
        return clientDtoList;

    }

//    public static ClientDto toClientDto(CreateOrderDto createOrderDto) {
//        return ClientDto.builder()
//                .firstName(createOrderDto.getFirstName())
//                .lastName(createOrderDto.getLastName())
//                .phoneNumber(createOrderDto.getPhoneNumber())
//                .build();
//    }
    public static ClientEntity update(ClientEntity clientEntity, ClientDto clientDto){
        clientEntity.setFirstName(clientDto.getFirstName());
        clientEntity.setLastName(clientDto.getLastName());
        clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
        return clientEntity;
    }
}
