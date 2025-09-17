package com.servico_de_usuario.service.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(){
        super("Cartão não encontrado");
    }
}
