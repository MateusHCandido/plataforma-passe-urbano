package com.servico_de_usuario.service.dto;

import com.servico_de_usuario.entity.AuditLog;

import java.time.LocalDateTime;

public class AuditLogDTO {

    private String usuarioEmail;
    private String acao;
    private String entidade;
    private Long entidadeId;
    private LocalDateTime timestamp;
    private String detalhe;

    public AuditLogDTO(AuditLog auditLog){
        this.usuarioEmail = auditLog.getUsuarioEmail();
        this.acao = auditLog.getAcao();
        this.entidade = auditLog.getEntidade();
        this.entidadeId = auditLog.getEntidadeId();
        this.timestamp = auditLog.getTimestamp();
        this.detalhe = auditLog.getDetalhe();
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public String getAcao() {
        return acao;
    }

    public String getEntidade() {
        return entidade;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
