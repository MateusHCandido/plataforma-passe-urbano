package com.servico_de_usuario.service.dto;

public class CartaoRequestDTO {

    private String emailUsuario;
    private String tipoCartao;

    public CartaoRequestDTO(String emailUsuario, String tipoCartao) {
        this.emailUsuario = emailUsuario;
        this.tipoCartao = tipoCartao;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }
}
