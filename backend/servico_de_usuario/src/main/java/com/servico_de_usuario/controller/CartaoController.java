package com.servico_de_usuario.controller;

import com.servico_de_usuario.service.CartaoService;
import com.servico_de_usuario.service.dto.CartaoRequestDTO;
import com.servico_de_usuario.service.dto.CartaoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;


    //Cria cartões: Só o admin pode criar
    @PostMapping("/adicionar")
    public ResponseEntity<String> criarCartao(
            @RequestBody CartaoRequestDTO request,
            @RequestParam("emailUsuario") String emailUsuario){
        cartaoService.adicionarCartao(request, emailUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cartão criado com sucesso!");
    }

    @GetMapping("/buscar/{numeroCartao}")
    public ResponseEntity<CartaoResponseDTO> buscarCartaoByNumero(@PathVariable("numeroCartao") Long numeroCartao){
        return ResponseEntity.status(HttpStatus.OK).body(cartaoService.buscarCartaoByNumero(numeroCartao));
    }

    //Deleta cartões: o usuário só pode deleter o próprio e admin todos
    @DeleteMapping("/remover/{numeroCartao}")
    public ResponseEntity<String> removerCartao(
            @PathVariable("numeroCartao") Long numeroCartao,
            @RequestParam("emailUsuario") String emailUsuario){
        cartaoService.removerCartao(numeroCartao, emailUsuario);

        return ResponseEntity.status(HttpStatus.OK).body("Cartão removido com sucesso");
    }

    //Lista cartões: o admin lista todos os cartões
    @GetMapping("/listar")
    public ResponseEntity<List<CartaoResponseDTO>> listarCartoes(){
        List<CartaoResponseDTO> cartoes = cartaoService.listarCartoes();

        if (cartoes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(cartoes);
    }

    //Lista cartões: o usuário lista seus próprios cartões
    @GetMapping("/listar/by/email")
    public ResponseEntity<List<CartaoResponseDTO>> listarCarotesUsuario(@RequestBody CartaoRequestDTO request){
        List<CartaoResponseDTO> cartoes = cartaoService.listarCartoesByEmail(request.getEmailUsuario());

        if (cartoes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(cartoes);
    }

    @PutMapping("/ativar/inativar/{numeroCartao}")
    public ResponseEntity<String> ativarInativaCartao(
            @PathVariable("numeroCartao") Long numeroCartao,
            @RequestParam("emailUsuario") String emailUsuario){
        boolean statusCartao = cartaoService.ativarInativarCartao(numeroCartao, emailUsuario);

        if (statusCartao) {
            return ResponseEntity.ok("Alteração de estado para ATIVO realizada");
        };

        return ResponseEntity.ok("Alteração de estado para BLOQUEADO realizada");
    }
}
