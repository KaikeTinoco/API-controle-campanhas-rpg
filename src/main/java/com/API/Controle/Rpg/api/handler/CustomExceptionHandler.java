package com.API.Controle.Rpg.api.handler;

import com.API.Controle.Rpg.api.domain.dtos.ErrorResponseDTO;
import com.API.Controle.Rpg.api.exceptions.BadRequestException;
import com.API.Controle.Rpg.api.exceptions.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        ErrorResponseDTO dto = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBadRequestException(BadRequestException e){
        ErrorResponseDTO dto = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorResponseDTO dto = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .messages(ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
