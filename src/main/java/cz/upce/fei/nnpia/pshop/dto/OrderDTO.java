package cz.upce.fei.nnpia.pshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private OrderAddressDTO address;
    private String shippingMethod;
    private String paymentMethod;
    private List<ItemDTO> items;
    private Double price;
}
