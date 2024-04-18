package cz.upce.fei.nnpia.pshop.dto;

import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private String type;
    private Integer count;

    public ItemE getType() {
        return ItemE.valueOf(type);
    }
}
