package com.tcc.doman.exception;

public class ValidacaoException extends BusinessException {

    public ValidacaoException(String message) {
        super(message);
    }

    public ValidacaoException() {
        super("");
    }

    public ValidacaoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}