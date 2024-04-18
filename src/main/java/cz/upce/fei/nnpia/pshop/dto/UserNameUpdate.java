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
public class UserNameUpdate {
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime updateDate;
}
