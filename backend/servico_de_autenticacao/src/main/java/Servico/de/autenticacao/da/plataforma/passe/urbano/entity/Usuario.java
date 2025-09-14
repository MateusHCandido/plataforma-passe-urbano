package Servico.de.autenticacao.da.plataforma.passe.urbano.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "usuarios", schema = "auth")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Permissao permissao;


    public Usuario(){}

    public Usuario(Long id, String nome, String email, String senha, Permissao permissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @PrePersist
    public void prePersist(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senha);
    }
}
