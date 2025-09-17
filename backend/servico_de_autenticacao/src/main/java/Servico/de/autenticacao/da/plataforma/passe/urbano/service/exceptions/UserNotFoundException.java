package Servico.de.autenticacao.da.plataforma.passe.urbano.service.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
