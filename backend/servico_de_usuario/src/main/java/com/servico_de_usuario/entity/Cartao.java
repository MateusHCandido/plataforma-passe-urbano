package com.servico_de_usuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.servico_de_usuario.entity.enums.TipoCartao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartaoId;
    @Column(name = "numero_cartao", unique = true, nullable = false,
            insertable = false, updatable = false)
    private Long numeroCartao;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCartao tipoCartao;
    @Column(nullable = false)
    private Boolean statusCartao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public Cartao(){}

    public Cartao(String nome, TipoCartao tipoCartao) {
        this.nome = nome;
        this.tipoCartao = tipoCartao;
    }

    public Cartao(Long cartaoId, Long numeroCartao, String nome, TipoCartao tipoCartao) {
        this.cartaoId = cartaoId;
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.tipoCartao = tipoCartao;
    }




    public Long getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(Long cartaoId) {
        this.cartaoId = cartaoId;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(TipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public Boolean getStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(Boolean statusCartao) {
        this.statusCartao = statusCartao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
