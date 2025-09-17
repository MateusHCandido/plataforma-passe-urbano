package com.servico_de_usuario.repository;

import com.servico_de_usuario.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findAllByOrderByTimestampDesc();

    List<AuditLog> findByUsuarioEmailOrderByTimestampDesc(String usuarioLogado);
}
