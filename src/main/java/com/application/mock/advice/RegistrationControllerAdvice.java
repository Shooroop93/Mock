package com.application.mock.advice;

import com.application.mock.constant.Status;
import com.application.mock.controller.RegistrationController;
import com.application.mock.dto.response.CreateUserResponseDTO;
import com.application.mock.utils.BindingErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RegistrationController.class)
@Slf4j
public class RegistrationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CreateUserResponseDTO> handleValidationExceptions(MethodArgumentNotValidException error) {
        StringBuilder errorMsg = new StringBuilder();
        BindingErrorUtil.getErrorMessage(errorMsg, error);
        CreateUserResponseDTO response = CreateUserResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(Status.ERROR.name())
                .description(errorMsg.toString())
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}