package com.servico_de_usuario.service.dto;


import javax.validation.constraints.Size;

public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;

    public UsuarioRequestDTO() {}

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Size(max = 12, message = "A senha deve ter no máximo 12 caracteres")
    public String getSenha() {
        return senha;
    }


    public void validarNome() {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres");
        }
    }

    public void validarEmail() {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("O email não é válido");
        }
    }

    public void validarSenha() {
        if (senha == null || senha.length() > 12) {
            throw new IllegalArgumentException("A senha deve ter no máximo 12 caracteres");
        }
    }

    public void validarDadosRequest() {
        validarNome();
        validarEmail();
        validarSenha();
    }
}