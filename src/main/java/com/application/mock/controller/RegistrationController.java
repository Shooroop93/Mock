package com.application.mock.controller;

import com.application.mock.dto.requst.CreateUserRequestDTO;
import com.application.mock.dto.response.CreateUserResponseDTO;
import com.application.mock.service.UserCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserCreateService userCreateService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        CreateUserResponseDTO response = userCreateService.createUserAndReturnResponse(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}