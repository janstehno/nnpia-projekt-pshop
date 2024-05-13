package cz.upce.fei.nnpia.pshop.dto;

import cz.upce.fei.nnpia.pshop.entity.enums.OrderE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinimalOrderDTO {
    private Long id;
    private Double shippingPrice;
    private Double paymentPrice;
    private List<MinimalOrderItemDTO> items;
    private OrderE state;
}
