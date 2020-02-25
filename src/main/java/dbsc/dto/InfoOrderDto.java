package dbsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoOrderDto {

    private Integer id;
    private String nameDevice;
    private String typeDevice;
    private Date dateCreate;
    private String status;
    private String note;
    private Double totalCost;
    private String malfunction;
    private ClientDto client;
    private List<PriceOfWorkDto> priceOfWorkDtoList;

}
