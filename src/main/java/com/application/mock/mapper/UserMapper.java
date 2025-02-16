package com.application.mock.mapper;

import com.application.mock.dto.requst.CreateUserRequestDTO;
import com.application.mock.model.UserTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "email", source = "contacts.email")
    @Mapping(target = "phone", source = "contacts.phone")
    CreateUserRequestDTO usersTableToCreateUserRequestDTO(UserTable usersTable);

    // Маппинг из DTO CreateUserRequestDTO в сущность UsersTable
    @Mapping(target = "contacts.email", source = "email")
    @Mapping(target = "contacts.phone", source = "phone")
    UserTable createUserRequestDTOToUsersTable(CreateUserRequestDTO createUserRequestDTO);

}