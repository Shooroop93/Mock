package com.application.mock.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(value = NON_NULL)
public class CreateUserResponseDTO {

    @JsonProperty("user_id")
    private Long id;
    @JsonProperty("status_code")
    private int statusCode;
    private String status;
    private String description;
}