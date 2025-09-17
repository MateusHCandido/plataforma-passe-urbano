package com.servico_de_usuario.service.dto;

import com.servico_de_usuario.entity.Cartao;
import com.servico_de_usuario.entity.enums.TipoCartao;

public class CartaoResponseDTO {

    private Long cartaoId;
    private Long numeroCartao;
    private String nome;
    private TipoCartao tipoCartao;
    private Boolean statusCartao;

    public CartaoResponseDTO(Cartao cartao){
        this.cartaoId = cartao.getCartaoId();
        this.numeroCartao = cartao.getNumeroCartao();;
        this.nome = cartao.getNome();
        this.tipoCartao = cartao.getTipoCartao();
        this.statusCartao = cartao.getStatusCartao();
    }


    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public String getNome() {
        return nome;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public Boolean getStatusCartao() {
        return statusCartao;
    }
}
