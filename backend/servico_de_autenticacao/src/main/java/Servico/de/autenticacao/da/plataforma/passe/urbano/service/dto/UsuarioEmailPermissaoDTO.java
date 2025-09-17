package Servico.de.autenticacao.da.plataforma.passe.urbano.service.dto;

import Servico.de.autenticacao.da.plataforma.passe.urbano.entity.Usuario;

public class UsuarioEmailPermissaoDTO {
    private String email;
    private String permissao;

    public UsuarioEmailPermissaoDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.permissao = usuario.getPermissao().getNome();
    }

    public String getEmail() { return email; }
    public String getPermissao() { return permissao; }
}
