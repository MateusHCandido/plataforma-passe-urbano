package com.servico_de_usuario.service;

import com.servico_de_usuario.entity.Usuario;
import com.servico_de_usuario.repository.UsuarioRepository;
import com.servico_de_usuario.service.dto.UsuarioRequestDTO;
import com.servico_de_usuario.service.dto.UsuarioResponseDTO;
import com.servico_de_usuario.service.exceptions.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuditLogService auditLogService;


    public List<UsuarioResponseDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void alterarUsuario(UsuarioRequestDTO request, String emailUsuario){
        String novoNomeUsuario = request.getNome();
        String novaSenha = request.getSenha();
        // usa email para trocar novoNomeUsuario ou novaSenha
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        Long usuarioId = usuario.getUsuarioId();

        if (novoNomeUsuario != null && !novoNomeUsuario.isEmpty()){
            usuarioRepository.updateNomeUsuarioCartao(usuario.getEmail(), novoNomeUsuario);
            usuarioRepository.updateNomeUsuarioAcesso(usuario.getEmail(), novoNomeUsuario);
        }


        if (novaSenha != null && !novaSenha.isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCriptografada = encoder.encode(novaSenha);

            usuarioRepository.updateSenhaUsuarioCartao(usuario.getEmail(), senhaCriptografada);
            usuarioRepository.updateSenhaUsuarioAcesso(usuario.getEmail(), senhaCriptografada);
        }

        Usuario usuarioAlterado = usuarioRepository.save(usuario);
        auditLogService.registrarAcao(
                emailUsuario,
                "ALTERAR_USUARIO",
                "USUARIO",
                usuarioId,
                "path: /usuario/alterar"
        );

    }

    public void removerUsuario(String emailParaDeletar, String emailUsuario){
        Usuario usuario = usuarioRepository.findByEmail(emailParaDeletar)
                .orElseThrow(EmailNotFoundException::new);

        Long usuarioId = usuario.getUsuarioId();

        usuarioRepository.delete(usuario);
        usuarioRepository.deleteUsuarioByEmail(emailParaDeletar);
        auditLogService.registrarAcao(
                emailUsuario,
                "REMOVER_USUARIO",
                "USUARIO",
                usuarioId,
                "path: /usuario/deletar?email="
        );
    }


    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        return new UsuarioResponseDTO(usuario);
    }
}
