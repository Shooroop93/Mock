package com.application.mapper;

import com.application.mock.dto.requst.CreateUserRequestDTO;
import com.application.mock.mapper.UserMapperImpl;
import com.application.mock.model.UserContact;
import com.application.mock.model.UserTable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ImportAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UserMapperTest {

    private final String firstName = "TestTestTestTestTestTestTestTestTestTestTestTest1#";
    private final String middleName = "TestTestTestTestTestTestTest";
    private final String lastName = "t";
    private final String email = "qwerty12345@test.test";
    private final String phone = "+12345678910";

    @Test
    public void check_1() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .email(email)
                .phone(phone).build();

        UserTable userTableDB = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(userTableDB);
        assertEquals(firstName, userTableDB.getFirstName());
        assertEquals(middleName, userTableDB.getMiddleName());
        assertEquals(lastName, userTableDB.getLastName());
        assertEquals(email, userTableDB.getContacts().getEmail());
        assertEquals(phone, userTableDB.getContacts().getPhone());
    }

    @Test
    public void check_2() {
        UserTable userTable = new UserTable();
        userTable.setFirstName(firstName);
        userTable.setMiddleName(middleName);
        userTable.setLastName(lastName);

        UserContact userContact = new UserContact();
        userContact.setEmail(email);
        userContact.setPhone(phone);

        userTable.setContacts(userContact);

        CreateUserRequestDTO requestActual = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);

        CreateUserRequestDTO requestExpected = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .email(email)
                .phone(phone).build();

        assertNotNull(requestActual);
        assertEquals(requestExpected, requestActual);

    }

    @Test
    public void check_3() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .email(email)
                .phone(phone).build();

        UserTable userTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        CreateUserRequestDTO actualRequest = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);

        assertNotNull(actualRequest);
        assertEquals(request, actualRequest);
    }

    @Test
    public void check_4() {
        UserTable userTable = new UserTable();
        userTable.setFirstName(firstName);
        userTable.setMiddleName(middleName);
        userTable.setLastName(lastName);

        UserContact userContact = new UserContact();
        userContact.setEmail(email);
        userContact.setPhone(phone);

        userTable.setContacts(userContact);

        CreateUserRequestDTO request = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);
        UserTable actualdUserTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(actualdUserTable);
        assertEquals(firstName, actualdUserTable.getFirstName());
        assertEquals(middleName, actualdUserTable.getMiddleName());
        assertEquals(lastName, actualdUserTable.getLastName());
        assertEquals(email, actualdUserTable.getContacts().getEmail());
        assertEquals(phone, actualdUserTable.getContacts().getPhone());
    }

    @Test
    public void check_5() {
        CreateUserRequestDTO request = null;
        UserTable userTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);
        assertNull(userTable);
    }

    @Test
    public void check_6() {
        UserTable userTable = null;
        CreateUserRequestDTO request = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);
        assertNull(request);
    }

    @Test
    public void check_7() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName).build();

        UserTable userTableDB = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(userTableDB);
        assertEquals(firstName, userTableDB.getFirstName());
        assertEquals(middleName, userTableDB.getMiddleName());
        assertEquals(lastName, userTableDB.getLastName());
        assertNull(userTableDB.getContacts().getPhone());
        assertNull(userTableDB.getContacts().getEmail());
    }

    @Test
    public void check_8() {
        UserTable userTable = new UserTable();
        userTable.setFirstName(firstName);
        userTable.setMiddleName(middleName);
        userTable.setLastName(lastName);

        CreateUserRequestDTO request = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);

        assertNotNull(request);
        assertEquals(firstName, request.getFirstName());
        assertEquals(middleName, request.getMiddleName());
        assertEquals(lastName, request.getLastName());
        assertNull(request.getEmail());
        assertNull(request.getPhone());
    }

    @Test
    public void check_9() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .email(email)
                .build();

        UserTable userTableDB = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);
        assertNotNull(userTableDB);
        assertNotNull(userTableDB.getContacts());
        assertEquals(email, userTableDB.getContacts().getEmail());
        assertNull(userTableDB.getContacts().getPhone());
    }

    @Test
    public void check_10() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .phone(phone)
                .build();

        UserTable actualdUserTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(actualdUserTable);
        assertEquals(firstName, actualdUserTable.getFirstName());
        assertEquals(middleName, actualdUserTable.getMiddleName());
        assertEquals(lastName, actualdUserTable.getLastName());
        assertEquals(phone, actualdUserTable.getContacts().getPhone());
    }

    @Test
    public void check_11() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(firstName)
                .phone(phone)
                .build();

        UserTable actualdUserTable = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(actualdUserTable);
        assertEquals(firstName, actualdUserTable.getFirstName());
        assertEquals(phone, actualdUserTable.getContacts().getPhone());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "                                                "})
    public void check_12(String testValue) {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(testValue)
                .middleName(testValue)
                .lastName(testValue)
                .email(testValue)
                .phone(testValue).build();

        UserTable userTableDB = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(userTableDB);
        assertEquals(testValue, userTableDB.getFirstName());
        assertEquals(testValue, userTableDB.getMiddleName());
        assertEquals(testValue, userTableDB.getLastName());
        assertEquals(testValue, userTableDB.getContacts().getEmail());
        assertEquals(testValue, userTableDB.getContacts().getPhone());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "                                                "})
    public void check_13(String testValue) {
        UserTable userTable = new UserTable();
        userTable.setFirstName(testValue);
        userTable.setMiddleName(testValue);
        userTable.setLastName(testValue);

        UserContact userContact = new UserContact();
        userContact.setEmail(testValue);
        userContact.setPhone(testValue);

        userTable.setContacts(userContact);

        CreateUserRequestDTO requestActual = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);

        CreateUserRequestDTO requestExpected = CreateUserRequestDTO.builder()
                .firstName(testValue)
                .middleName(testValue)
                .lastName(testValue)
                .email(testValue)
                .phone(testValue).build();

        assertNotNull(requestActual);
        assertEquals(requestExpected, requestActual);
    }

    @ParameterizedTest
    @NullSource
    public void check_14(String testValue) {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .firstName(testValue)
                .middleName(testValue)
                .lastName(testValue)
                .email(testValue)
                .phone(testValue).build();

        UserTable userTableDB = UserMapperImpl.INSTANCE.createUserRequestDTOToUsersTable(request);

        assertNotNull(userTableDB);
        assertNull(userTableDB.getFirstName());
        assertNull(userTableDB.getMiddleName());
        assertNull(userTableDB.getLastName());
        assertNull(userTableDB.getContacts().getEmail());
        assertNull(userTableDB.getContacts().getPhone());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void check_15(String testValue) {
        UserTable userTable = new UserTable();
        userTable.setFirstName(testValue);
        userTable.setMiddleName(testValue);
        userTable.setLastName(testValue);

        UserContact userContact = new UserContact();
        userContact.setEmail(testValue);
        userContact.setPhone(testValue);

        userTable.setContacts(userContact);

        CreateUserRequestDTO requestActual = UserMapperImpl.INSTANCE.usersTableToCreateUserRequestDTO(userTable);

        CreateUserRequestDTO requestExpected = CreateUserRequestDTO.builder()
                .firstName(testValue)
                .middleName(testValue)
                .lastName(testValue)
                .email(testValue)
                .phone(testValue).build();

        assertNotNull(requestActual);
        assertEquals(requestExpected, requestActual);
    }
}