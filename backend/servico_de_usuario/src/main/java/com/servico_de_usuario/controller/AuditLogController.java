package com.servico_de_usuario.controller;

import com.servico_de_usuario.service.AuditLogService;
import com.servico_de_usuario.service.dto.AuditLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping("/logs")
    public List<AuditLogDTO> listarLogs(
            @RequestParam("emailUsuario") String emailUsuario,
            @RequestParam("tipoUsuario") String tipoUsuario) {


        return auditLogService.listarLogsDoUsuario(emailUsuario, tipoUsuario);
    }
}
