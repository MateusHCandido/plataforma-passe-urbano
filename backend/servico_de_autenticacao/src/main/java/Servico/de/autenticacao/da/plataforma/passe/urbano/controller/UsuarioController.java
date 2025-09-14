package Servico.de.autenticacao.da.plataforma.passe.urbano.controller;

import Servico.de.autenticacao.da.plataforma.passe.urbano.service.UsuarioLoginService;
import Servico.de.autenticacao.da.plataforma.passe.urbano.service.dto.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario-autenticado")
public class UsuarioController {

    @Autowired
    private UsuarioLoginService usuarioService;


    @PostMapping(path = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarUsuario(@RequestBody UsuarioRequest request){
        usuarioService.salvarUsuario(request);
        return "Usuário cadastrado com sucesso";
    }
}
