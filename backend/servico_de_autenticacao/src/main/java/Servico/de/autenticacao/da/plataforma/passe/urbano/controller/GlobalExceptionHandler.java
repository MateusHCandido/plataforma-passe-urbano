package Servico.de.autenticacao.da.plataforma.passe.urbano.controller;


import Servico.de.autenticacao.da.plataforma.passe.urbano.controller.exceptions.ExceptionResponse;
import Servico.de.autenticacao.da.plataforma.passe.urbano.service.exceptions.UserNotFoundException;
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

    //Tratamento de exception response para usuário não encontrado
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerEmailNotFoundException(UserNotFoundException ex,
                                                                           WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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

