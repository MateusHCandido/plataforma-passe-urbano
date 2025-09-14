package com.servico_de_usuario.service.factory;

import com.servico_de_usuario.entity.Cartao;
import com.servico_de_usuario.entity.enums.TipoCartao;


public class CartaoFactory {

    public static Cartao criarCartao(String nome, TipoCartao tipoCartao) {
        if (tipoCartao == null) {
            throw new IllegalArgumentException("Tipo de cartão não pode ser nulo");
        }

        switch (tipoCartao) {
            case COMUM:
                return new Cartao(nome, TipoCartao.COMUM);
            case ESTUDANTE:
                return new Cartao(nome, TipoCartao.ESTUDANTE);
            case TRABALHADOR:
                return new Cartao(nome, TipoCartao.TRABALHADOR);
            default:
                throw new IllegalArgumentException("Tipo de cartão inválido: " + tipoCartao);
        }
    }

}
