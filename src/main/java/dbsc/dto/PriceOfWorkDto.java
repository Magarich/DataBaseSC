package dbsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceOfWorkDto {

    private Integer id;
    private Date dateAdd;
    private String kindOfWork;
    private Double price = 0D;
}
