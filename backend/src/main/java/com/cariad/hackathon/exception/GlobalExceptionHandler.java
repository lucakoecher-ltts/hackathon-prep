package com.cariad.hackathon.exception;

import com.cariad.hackathon.common.APIResponse;
import com.cariad.hackathon.common.ErrorDetails;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleAlreadyInEcoModeException.class)
    public ResponseEntity<String> handleVehicleAlreadyInEcoMode(VehicleAlreadyInEcoModeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public APIResponse<Void> handleException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return APIResponse
                .error("An unexpected error occurred", 500,
                        new ErrorDetails("INTERNAL_SERVER_ERROR",
                                ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public APIResponse<Object> handleValidationException(Exception ex) {
        log.error("Validation error occurred: {}", ex.getMessage(), ex);
        return APIResponse
                .error("Validation error occurred", 400,
                        new ErrorDetails("VALIDATION_ERROR",
                                ex.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public APIResponse<Void> handleNoResourceFoundException(Exception ex) {
        log.error("Resource not found: {}", ex.getMessage(), ex);
        return APIResponse
                .error("Resource not found", 404,
                        new ErrorDetails("NOT_FOUND",
                                ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public APIResponse<Void> handleUnauthorizedException(Exception ex) {
        log.error("Unauthorized access: {}", ex.getMessage(), ex);
        return APIResponse
                .error("Unauthorized", 401,
                        new ErrorDetails("UNAUTHORIZED",
                                ex.getMessage()));
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public APIResponse<Void> handleForbiddenException(Exception ex) {
        log.error("Forbidden access: {}", ex.getMessage(), ex);
        return APIResponse
                .error("Forbidden", 403,
                        new ErrorDetails("FORBIDDEN",
                                ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public APIResponse<Void> handleEntityNotFoundException(Exception ex) {
        log.error("Entity not found: {}", ex.getMessage(), ex);
        return APIResponse
                .error("Entity not found", 404,
                        new ErrorDetails("NOT_FOUND",
                                ex.getMessage()));
    }
}