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
    public ResponseEntity<UsuarioResponseDTO> autoConsulta(@Valid @RequestBody UsuarioRequestDTO request){

        return ResponseEntity.ok().body(usuarioService.findByEmail(request.getEmail()));
    }



    //alterar usuário: admin pode alterar os dados de todos, user só o próprio
    @PutMapping("/alterar")
    public ResponseEntity<String> alterarUsuario(@Valid @RequestBody UsuarioRequestDTO request){
        usuarioService.alterarUsuario(request);

        return ResponseEntity.ok("Usuário alterado com sucesso");
    }

    //remover usuário: admin pode deletar todos, usuario apenas o seu próprio
    @DeleteMapping("/deletar")
    public ResponseEntity<String> removerUsuario(@Valid @RequestBody UsuarioRequestDTO request){
        usuarioService.removerUsuario(request);

        return ResponseEntity.ok("Usuário removido com sucesso");
    }
}
