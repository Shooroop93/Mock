package com.application.mock.service;

import com.application.mock.dto.requst.CreateUserRequestDTO;
import com.application.mock.dto.response.CreateUserResponseDTO;
import com.application.mock.exception.CreateUserException;
import com.application.mock.mapper.UserMapperImpl;
import com.application.mock.model.UserTable;
import com.application.mock.repository.UserTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreateService {

    private final UserTableRepository userTableRepository;

    public CreateUserResponseDTO createUserAndReturnResponse(CreateUserRequestDTO request) {
        log.info("Received createUser request: {}", request);
        CreateUserResponseDTO.CreateUserResponseDTOBuilder builder = CreateUserResponseDTO.builder();
        UserTable userTable = null;
        try {
            userTable = createUser(request);
            log.info("User created successfully: {}", userTable);
        } catch (CreateUserException e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return builder.status("ERROR")
                    .statusCode(404)
                    .description(e.getMessage())
                    .build();
        }

        CreateUserResponseDTO response = builder.status("OK")
                .statusCode(200)
                .id(userTable.getId())
                .build();
        log.info("Returning response: {}", response);
        return response;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    protected UserTable createUser(CreateUserRequestDTO request) throws CreateUserException {
        log.debug("Mapping CreateUserRequestDTO to UserTable: {}", request);
        try {
            UserTable userTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);
            log.debug("Mapped UserTable: {}", userTable);
            UserTable savedUser = userTableRepository.save(userTable);
            log.info("User saved in repository with ID: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            log.error("Exception occurred while creating user: {}", e.getMessage(), e);
            throw new CreateUserException(e.getMessage(), e);
        }
    }
}