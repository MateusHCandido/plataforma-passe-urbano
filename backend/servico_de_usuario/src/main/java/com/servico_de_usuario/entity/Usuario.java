package com.servico_de_usuario.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario_cartao")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cartao> cartoes = new HashSet<>();


    public Usuario(){}

    public Usuario (String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Long usuarioId, String nome, String email, String senha, Set<Cartao> cartoes) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cartoes = cartoes;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(Set<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
}
