package com.askall.exception;

import com.askall.dto.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Genel Hatalar İçin (En son çare)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGeneralException(Exception e) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
    }



    // ✅ Validasyon Hataları İçin (DTO @Valid hataları)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ApiResponse.error(HttpStatus.BAD_REQUEST, "Validation error: " + errors);
    }

    // ✅ Parametre Geçersizse (Örn: UUID hatalı gelirse)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST, "Invalid input: " + e.getMessage());
    }
}
