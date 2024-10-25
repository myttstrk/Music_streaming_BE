package com.musicstreaming.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicstreaming.utils.MessageKeys;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = MessageKeys.PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @NotBlank(message=MessageKeys.PASSWORD_REQUIRED)
    private String password;

}
