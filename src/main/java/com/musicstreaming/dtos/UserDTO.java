package com.musicstreaming.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.musicstreaming.utils.MessageKeys;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("fullName")
    private String fullname;    

    @JsonProperty("phone_number")
    @NotBlank(message = MessageKeys.PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @JsonProperty("address")
    @NotBlank(message = MessageKeys.PHONE_NUMBER_REQUIRED)
    private String address;

    @NotBlank(message = MessageKeys.PASSWORD_REQUIRED)
    private String password;

    @JsonProperty("retype_password")
    @NotBlank(message = MessageKeys.RETYPE_PASSWORD_REQUIRED)
    private String retypePassword;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("facebook_account_id")
    private Long facebookAccountId;

    @JsonProperty("google_account_id")
    private Long googleAccountId;

    @JsonProperty("role_id")
    @NotNull(message = MessageKeys.ROLE_ID_REQUIRED)
    private Long RoleId;
}
