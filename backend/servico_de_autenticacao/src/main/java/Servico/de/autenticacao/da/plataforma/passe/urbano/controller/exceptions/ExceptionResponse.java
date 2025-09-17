package Servico.de.autenticacao.da.plataforma.passe.urbano.controller.exceptions;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String mensagem;
    private String descricao;

    public ExceptionResponse(Date date, String mensagem, String descricao) {
        this.timestamp = date;
        this.mensagem = mensagem;
        this.descricao = descricao;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDescricao() {
        return descricao;
    }
}
