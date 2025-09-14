package com.servico_de_usuario.service.dto;

import com.servico_de_usuario.entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioResponseDTO {

    private Long usuarioId;
    private String nome;
    private String email;
    private List<CartaoResponseDTO> cartoes;

    public UsuarioResponseDTO(){}

    public UsuarioResponseDTO(Usuario usuario){
        this.usuarioId = usuario.getUsuarioId();;
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cartoes = usuario.getCartoes()
                .stream()
                .map(CartaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public List<CartaoResponseDTO> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoResponseDTO> cartoes) {
        this.cartoes = cartoes;
    }
}
