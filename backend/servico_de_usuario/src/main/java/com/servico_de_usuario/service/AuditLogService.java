package com.servico_de_usuario.service;

import com.servico_de_usuario.service.dto.AuditLogDTO;
import com.servico_de_usuario.entity.AuditLog;
import com.servico_de_usuario.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository logRepository;

    public void registrarAcao(String usuarioEmail, String acao, String entidade, Long entidadeId, String detalhe){
        AuditLog log = new AuditLog();
        log.setUsuarioEmail(usuarioEmail);
        log.setAcao(acao);
        log.setEntidade(entidade);
        log.setEntidadeId(entidadeId);
        log.setTimestamp(LocalDateTime.now());
        log.setDetalhe(detalhe);

        logRepository.save(log);
    }

    public List<AuditLogDTO> listarLogsDoUsuario(String usuarioLogado, String permissaoUsuario) {
        List<AuditLog> logs;

        if (permissaoUsuario.equals("ROLE_ADMIN")) {
            logs = logRepository.findAllByOrderByTimestampDesc();
        } else {
            logs = logRepository.findByUsuarioEmailOrderByTimestampDesc(usuarioLogado);
        }

        return logs.stream()
                .map(AuditLogDTO::new)
                .collect(Collectors.toList());
    }
}
