package com.servico_de_usuario.service.exceptions;


public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email não encontrado");
    }

}
