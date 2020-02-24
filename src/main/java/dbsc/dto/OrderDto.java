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
public class OrderDto {
    private Integer id;
    private String nameDevice;
    private String typeDevice;
    private Date date;
    private String status;
    private String note;
    private Double price;
    private String malfunction;
}
