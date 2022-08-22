package com.tcc.doman.exception;

import com.tcc.api.exceptionhandler.ProblemType;
import org.springframework.http.HttpStatus;

public class EntidadeNaoEncontradaException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem, HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO);
    }

    public EntidadeNaoEncontradaException(Integer id) {
        this(String.format("Não existe um cadastro  com o código %d", id));
    }

}
