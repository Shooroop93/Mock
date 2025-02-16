package com.application.mock.utils;

import org.springframework.validation.BindException;

public class BindingErrorUtil {

    public static void getErrorMessage(StringBuilder builder, BindException error) {
        error.getBindingResult().getFieldErrors().forEach(fieldError ->
                builder.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ")
        );
    }
}