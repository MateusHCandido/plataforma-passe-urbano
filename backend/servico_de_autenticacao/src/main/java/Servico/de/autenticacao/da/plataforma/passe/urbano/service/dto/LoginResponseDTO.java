package Servico.de.autenticacao.da.plataforma.passe.urbano.service.dto;

import java.util.List;

public class LoginResponseDTO {
    private String token;
    private String tipo = "Bearer";
    private Long idUsuario;
    private String email;
    private List<String> roles;

    public void setToken(String token) {
        this.token = token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
