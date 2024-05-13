package cz.upce.fei.nnpia.pshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddressDTO {
    private String street;
    private String city;
    private Integer zipCode;
    private Integer phone;
}
