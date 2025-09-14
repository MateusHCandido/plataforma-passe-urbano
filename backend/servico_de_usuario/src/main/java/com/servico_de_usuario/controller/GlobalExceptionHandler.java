package com.servico_de_usuario.controller;

import com.servico_de_usuario.controller.exceptions.ExceptionResponse;
import com.servico_de_usuario.service.exceptions.CardNotFoundException;
import com.servico_de_usuario.service.exceptions.CardTypeAlreadyExistsException;
import com.servico_de_usuario.service.exceptions.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratamento de exception response para IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handlerIllegalException(IllegalArgumentException ex, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
          new Date(),
          ex.getMessage(),
          request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Tratamento de exception response para email não encontrado
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerEmailNotFoundException(EmailNotFoundException ex,
                                                                                 WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Trtatamento de exceção para cartão não encontrado
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ExceptionResponse> cardNotFoundException(CardNotFoundException ex,
                                                                   WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //tratamento de exceção para quando o usuário tenta registrar outro cartão do mesmo tipo
    @ExceptionHandler(CardTypeAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> cardTypeAlreadyExistException(CardTypeAlreadyExistsException ex,
                                                                           WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //trata uma exceção de forma global quando não identificada no tratameto
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllException(Exception exception, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
          new Date(),
                exception.getMessage(),
          request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

