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


    public List<UsuarioResponseDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void alterarUsuario(UsuarioRequestDTO request){
        String novoNomeUsuario = request.getNome();
        String novaSenha = request.getSenha();
        // usa email para trocar novoNomeUsuario ou novaSenha
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(EmailNotFoundException::new);

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

        usuarioRepository.save(usuario);
    }

    public void removerUsuario(UsuarioRequestDTO request){
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        if (usuario == null) throw new EmailNotFoundException();

        usuarioRepository.delete(usuario);
    }


    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        return new UsuarioResponseDTO(usuario);
    }
}
