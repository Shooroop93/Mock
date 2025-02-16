package com.application.mock.dto.requst;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequestDTO {

    @NotNull(message = "First_name не должен быть null")
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @NotNull(message = "Email не должен быть null")
    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;
    private String phone;
    @NotNull(message = "Password не должен быть null")
    @Size(min = 6, message = "Некорректный формат password, введите пароль от 5 символов")
    private String password;
}