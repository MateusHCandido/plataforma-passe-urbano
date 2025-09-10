package Servico.de.autenticacao.da.plataforma.passe.urbano.service;


import Servico.de.autenticacao.da.plataforma.passe.urbano.entity.Permissao;
import Servico.de.autenticacao.da.plataforma.passe.urbano.entity.Usuario;
import Servico.de.autenticacao.da.plataforma.passe.urbano.repository.PermissaoRepository;
import Servico.de.autenticacao.da.plataforma.passe.urbano.repository.UsuarioRepository;
import Servico.de.autenticacao.da.plataforma.passe.urbano.service.dto.UsuarioRequest;
import Servico.de.autenticacao.da.plataforma.passe.urbano.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UsuarioLoginService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;


    public void salvarUsuario(UsuarioRequest request){
        boolean loginExistente = usuarioRepository.existsByEmail(request.getEmail());

        if( loginExistente ) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");

        Usuario usuario = new Usuario();
        Permissao permissao = permissaoRepository.findByNome(request.getPermissao());

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setPermissao(permissao);
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        String role = usuario.getPermissao().getNome();

        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(role)
                .build();
    }
}
