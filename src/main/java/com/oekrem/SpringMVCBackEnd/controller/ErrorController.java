package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Response.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@RestController
@ControllerAdvice
@Tag(name = "Global Error Handling")
public class ErrorController {

    @Operation(summary = "Method Argument Not Valid Exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(HttpClientErrorException exception) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Bad Request Exception: " + exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @Operation(summary = "Access Denied Exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Forbidden, User does not have permission", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("Access Denied: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }


    @Operation(summary = "Entity Not Found Exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Entity Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Entity Not Found Exception: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }


    @Operation(summary = "Method Not Allowed Exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Method Not Allowed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotAllowed(HttpClientErrorException exception) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message("Method Not Allowed: " + exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiErrorResponse);
    }

    @Operation(summary = "Data Access Exception - Redis Connection Failture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Redis Connection Failture", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleRedisException(DataAccessException ex){
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Data Access Exception - Redis connection failture: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @Operation(summary = "Handles generic errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .message("Exception : " + ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
