package Servico.de.autenticacao.da.plataforma.passe.urbano.repository;

import Servico.de.autenticacao.da.plataforma.passe.urbano.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO service.usuario_cartao (nome, email, senha) " +
            "VALUES (:nome, :email, :senha)", nativeQuery = true)
    void salvarUsuarioEmUsuarioService(
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("senha") String senha);

    boolean existsByEmail(String email);


}
