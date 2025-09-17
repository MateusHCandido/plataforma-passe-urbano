package com.servico_de_usuario.controller;

import com.servico_de_usuario.service.UsuarioService;
import com.servico_de_usuario.service.dto.UsuarioRequestDTO;
import com.servico_de_usuario.service.dto.UsuarioResponseDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    //consulta usuários: apenas o admin consegue
    @GetMapping("/consultar")
    public ResponseEntity<List<UsuarioResponseDTO>> consultarUsuario(){
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();

        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/auto/consulta")
    public ResponseEntity<UsuarioResponseDTO> autoConsulta( @RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }



    //alterar usuário: admin pode alterar os dados de todos, user só o próprio
    @PutMapping("/alterar")
    public ResponseEntity<String> alterarUsuario(
            @Valid @RequestBody UsuarioRequestDTO request,
            @RequestParam("emailUsuario") String emailUsuario){
        usuarioService.alterarUsuario(request, emailUsuario);

        return ResponseEntity.ok("Usuário alterado com sucesso");
    }

    //remover usuário: admin pode deletar todos, usuario apenas o seu próprio
    @DeleteMapping("/deletar")
    public ResponseEntity<String> removerUsuario(
            @RequestParam("email") String email,
            @RequestParam("emailUsuario") String emailUsuario){
        usuarioService.removerUsuario(email, emailUsuario);

        return ResponseEntity.ok("Usuário removido com sucesso");
    }
}
