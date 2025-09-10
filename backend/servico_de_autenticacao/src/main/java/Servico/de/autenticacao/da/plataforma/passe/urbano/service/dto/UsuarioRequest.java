package Servico.de.autenticacao.da.plataforma.passe.urbano.service.dto;

public class UsuarioRequest {

    private String nome;
    private String email;
    private String senha;
    private String permissao;

    public UsuarioRequest(){}

    public UsuarioRequest(String nome, String email, String senha, String permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
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

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}
