package dbsc.convecter;

import dbsc.dto.PriceOfWorkDto;
import dbsc.entity.PriceOfWorkEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PriceOfWorkConverter {
    public static PriceOfWorkEntity toPriceOfWorkEntity (PriceOfWorkDto priceOfWorkDto){
        return PriceOfWorkEntity.builder()
                .id(priceOfWorkDto.getId())
                .dateAdd(priceOfWorkDto.getDateAdd())
                .kindOfWork(priceOfWorkDto.getKindOfWork())
                .price(priceOfWorkDto.getPrice())
                .build();
    }

    public static PriceOfWorkDto toPriceOfWorkDto (PriceOfWorkEntity priceOfWorkEntity){
        return PriceOfWorkDto.builder()
                .id(priceOfWorkEntity.getId())
                .dateAdd(priceOfWorkEntity.getDateAdd())
                .kindOfWork(priceOfWorkEntity.getKindOfWork())
                .price(priceOfWorkEntity.getPrice())
                .build();
    }
    public static List<PriceOfWorkDto> toPriceOfWorkDto (List<PriceOfWorkEntity> priceOfWorkEntityList){
        List<PriceOfWorkDto> priceOfWorkDtoList = priceOfWorkEntityList.stream()
                .map(PriceOfWorkConverter::toPriceOfWorkDto)
                .collect(Collectors.toList());
        return priceOfWorkDtoList;
    }

}
