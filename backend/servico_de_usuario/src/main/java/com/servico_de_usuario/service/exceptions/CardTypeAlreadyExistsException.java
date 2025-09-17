package com.servico_de_usuario.service.exceptions;


public class CardTypeAlreadyExistsException extends RuntimeException {
    public CardTypeAlreadyExistsException(String tipoCartao, String email){
        super("O cartão do tipo " + tipoCartao + "  já está cadastrado para o email " + email);
    }
}
