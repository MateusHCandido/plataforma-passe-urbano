package com.servico_de_usuario.repository;

import com.servico_de_usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    //atualiza nome do usuario da tabela usuario_cartao
    @Modifying
    @Transactional
    @Query(value = "UPDATE service.usuario_cartao SET nome = :novoNome WHERE email = :email", nativeQuery = true)
    void updateNomeUsuarioCartao(@Param("email") String email, @Param("novoNome") String novoNome);

    //atualiza nome do usuario da tabela usuarios
    @Modifying
    @Transactional
    @Query(value = "UPDATE auth.usuarios SET nome = :novoNome WHERE email = :email", nativeQuery = true)
    void updateNomeUsuarioAcesso(@Param("email") String email, @Param("novoNome") String novoNome);

    //atualiza senha do usuário da tabela usario_cartao
    @Modifying
    @Transactional
    @Query(value = "UPDATE service.usuario_cartao SET senha = :novaSenha WHERE email = :email", nativeQuery = true)
    void updateSenhaUsuarioCartao(@Param("email") String email,@Param("novaSenha") String novaSenha);

    //atualiza senha do usuário da tabela usuario
    @Modifying
    @Transactional
    @Query(value = "UPDATE auth.usuarios SET senha = :novaSenha WHERE email = :email", nativeQuery = true)
    void updateSenhaUsuarioAcesso(@Param("email") String email, @Param("novaSenha") String novaSenha);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM auth.usuarios WHERE email = :email", nativeQuery = true)
    void deleteUsuarioByEmail(@Param("email") String email);
}
