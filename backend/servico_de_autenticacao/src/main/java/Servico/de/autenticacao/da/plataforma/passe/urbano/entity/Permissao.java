package Servico.de.autenticacao.da.plataforma.passe.urbano.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
