package cz.upce.fei.nnpia.pshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {
    private String password;
    private LocalDateTime updateDate;
}
