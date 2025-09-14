package com.servico_de_usuario.repository;

import com.servico_de_usuario.entity.Cartao;
import com.servico_de_usuario.entity.Usuario;
import com.servico_de_usuario.entity.enums.TipoCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Boolean existsByTipoCartaoAndUsuario(TipoCartao tipoCartao, Usuario usuario);

    Optional<Cartao> findByNumeroCartao(Long numeroCartao);

    //seleciona o maior ID cadastrado na tabela cartao
    @Query("SELECT MAX(c.cartaoId) FROM Cartao c")
    Optional<Long> findMaxCartaoId();
}

