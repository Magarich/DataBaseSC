package dbsc.convecter;

import dbsc.dto.MasterDto;
import dbsc.entity.MasterEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MasterConverter {
    public static MasterEntity toMasterEntity(MasterDto masterDto){
        return MasterEntity.builder()
                .name(masterDto.getName())
                .percentWork(masterDto.getPercent())
                .id(masterDto.getId())
                .build();
    }
    public static MasterDto toMasterDto(MasterEntity masterEntity){
        return MasterDto.builder()
                .name(masterEntity.getName())
                .percent(masterEntity.getPercentWork())
                .id(masterEntity.getId())
                .build();
    }

    public static List<MasterDto> toMasterDtos(List<MasterEntity> masterEntities) {
        List<MasterDto> masterDtos = masterEntities.stream()
                .map(MasterConverter::toMasterDto)
                .collect(Collectors.toList());
        return masterDtos;
    }

    public static MasterEntity update(MasterEntity masterEntity, MasterDto masterDto){
        masterEntity.setName(masterDto.getName());
        masterEntity.setPercentWork(masterDto.getPercent());
        return masterEntity;
    }
}
