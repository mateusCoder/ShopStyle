package com.mcm.mscustomer.handler;

import jakarta.ws.rs.*;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private static final String BAD_REQUEST_MESSAGE = "Client specified an invalid argument, request body or query param";
    private static final String FORBIDDEN_MESSAGE = "Authenticated user has no permission to access the requested resource";
    private static final String NOT_FOUND_MESSAGE = "A specified resource is not found";
    private static final String NOT_AUTHORIZED_MESSAGE = "User is not authorized to access the resource";
    private static final String INTERNAL_SERVER_MESSAGE = "Server Error";
    private static final String SOCKET_TIMEOUT_MESSAGE = "Request timeout exceeded. Try it later";
    private static final String PSQL_MESSAGE = "CPF or email already exists";

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("INVALID_ARGUMENT",BAD_REQUEST_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("INVALID_ARGUMENT",BAD_REQUEST_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PSQLException.class})
    public ResponseEntity<Object> PSQLException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("INVALID_ARGUMENT", PSQL_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Object> handleForbiddenException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("PERMISSION_DENIED", FORBIDDEN_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("NOT_FOUND", NOT_FOUND_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotAuthorizedException.class})
    public ResponseEntity<Object> handleNotAuthorizedException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("NOT_AUTHORIZED", NOT_AUTHORIZED_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("INTERNAL", INTERNAL_SERVER_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SocketTimeoutException.class})
    public ResponseEntity<Object> handleSocketTimeoutException(Exception e, WebRequest request){
        Map<String, String> errors = createResponse("TIMEOUT", SOCKET_TIMEOUT_MESSAGE);
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.GATEWAY_TIMEOUT);
    }

    public final Map<String, String> createResponse(String code, String message){
        LOGGER.error("[An exception occured] Code: {}, {}", code, message);
        Map<String, String> errors = new HashMap<>();
        errors.put("code", code);
        errors.put("message", message);
        return errors;
    }
}
