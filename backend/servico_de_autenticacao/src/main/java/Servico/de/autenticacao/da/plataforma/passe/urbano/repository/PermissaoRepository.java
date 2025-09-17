package Servico.de.autenticacao.da.plataforma.passe.urbano.repository;

import Servico.de.autenticacao.da.plataforma.passe.urbano.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Permissao findByNome(String nome);
}
