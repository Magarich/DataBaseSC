package dbsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {
    private String nameDevice;
    private String typeDevice;
    private String malfunction;
    private String note;
    private Integer clientId;
}

