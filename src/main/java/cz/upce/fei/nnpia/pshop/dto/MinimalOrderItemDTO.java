package cz.upce.fei.nnpia.pshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimalOrderItemDTO {
    private Long id;
    private Double price;
    private Integer count;
}
