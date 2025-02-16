package com.application.dto.validation;

import com.application.mock.dto.requst.CreateUserRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserRequestDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    public void check_1() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email("test@test.com")
                .phone("+1234567890")
                .password("qwerty12345")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void check_2() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName(null)
                .middleName("Test")
                .lastName("Testovich")
                .email("test@test.com")
                .phone("+1234567890")
                .password("qwerty12345")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean firstNameViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName")
                        && v.getMessage().equals("First_name не должен быть null"));
        assertTrue(firstNameViolation);
    }

    @Test
    public void check_3() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email(null)
                .phone("+1234567890")
                .password("qwerty12345")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean emailNotNullViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        && v.getMessage().equals("Email не должен быть null"));
        assertTrue(emailNotNullViolation);
    }

    @Test
    public void check_4() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email("")
                .phone("+1234567890")
                .password("qwerty12345")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean emailNotEmptyViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        && v.getMessage().equals("Email не должен быть пустым"));
        assertTrue(emailNotEmptyViolation);
    }

    @Test
    public void check_5() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email("invalid-email")
                .phone("+1234567890")
                .password("qwerty12345")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean emailFormatViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        && v.getMessage().equals("Некорректный формат email"));
        assertTrue(emailFormatViolation);
    }

    @Test
    public void check_6() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email("test@test.com")
                .phone("+1234567890")
                .password(null)
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean passwordNullViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        && v.getMessage().equals("Password не должен быть null"));
        assertTrue(passwordNullViolation);
    }

    @Test
    public void check_7() {
        CreateUserRequestDTO dto = CreateUserRequestDTO.builder()
                .firstName("Testov")
                .middleName("Test")
                .lastName("Testovich")
                .email("test@test.com")
                .phone("+1234567890")
                .password("123")
                .build();

        Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        boolean passwordSizeViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        && v.getMessage().contains("Некорректный формат password"));
        assertTrue(passwordSizeViolation);
    }
}