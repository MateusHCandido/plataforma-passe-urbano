package com.servico_de_usuario.service;

import com.servico_de_usuario.entity.Cartao;
import com.servico_de_usuario.entity.Usuario;
import com.servico_de_usuario.entity.enums.TipoCartao;
import com.servico_de_usuario.repository.CartaoRepository;
import com.servico_de_usuario.repository.UsuarioRepository;
import com.servico_de_usuario.service.dto.CartaoRequestDTO;
import com.servico_de_usuario.service.dto.CartaoResponseDTO;
import com.servico_de_usuario.service.exceptions.CardNotFoundException;
import com.servico_de_usuario.service.exceptions.CardTypeAlreadyExistsException;
import com.servico_de_usuario.service.exceptions.EmailNotFoundException;
import com.servico_de_usuario.service.factory.CartaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public void adicionarCartao(CartaoRequestDTO request){
        Usuario usuario = usuarioRepository.findByEmail(request.getEmailUsuario())
                .orElseThrow(EmailNotFoundException::new);

        String nomeCartao = usuario.getNome();
        TipoCartao tipoCartao = TipoCartao.valueOf(request.getTipoCartao());

        boolean tipoCartaoJaExiste = cartaoRepository
                .existsByTipoCartaoAndUsuario(tipoCartao, usuario);

        if (tipoCartaoJaExiste) {
            throw new CardTypeAlreadyExistsException(request.getTipoCartao(), request.getEmailUsuario());
        }

        Cartao cartao = CartaoFactory.criarCartao(nomeCartao, tipoCartao);
        cartao.setStatusCartao(true);
        cartao.setUsuario(usuario);

        Cartao cartaoSalvo = cartaoRepository.save(cartao);

        usuario.getCartoes().add(cartaoSalvo);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void removerCartao(Long numeroCartao){
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(CardNotFoundException::new);

        cartaoRepository.delete(cartao);

        // Ajusta a sequence automaticamente
        Long maxId = cartaoRepository.findMaxCartaoId().orElse(0L);
        entityManager.createNativeQuery(
                        "SELECT setval('service.cartao_cartao_id_seq', :maxId)")
                .setParameter("maxId", maxId)
                .getSingleResult();
    }

    public List<CartaoResponseDTO> listarCartoes(){
        return cartaoRepository.findAll().stream()
                .map(CartaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<CartaoResponseDTO> listarCartoesByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);

        return usuario.getCartoes().stream()
                .map(CartaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public boolean ativarInativarCartao(Long numeroCartao){
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(CardNotFoundException::new);

        cartao.setStatusCartao(!cartao.getStatusCartao());

        cartaoRepository.save(cartao);

        return cartao.getStatusCartao();
    }


}
