package com.fibbo.spring_app.config.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fibbo.spring_app.domain.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException ex) {
        Response response = new Response(ex.getMessage(), HttpStatus.BAD_REQUEST.name());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> HandleUsernameNotFoundException(UsernameNotFoundException ex){

        Response response = new Response(ex.getMessage(), HttpStatus.BAD_REQUEST.name());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}