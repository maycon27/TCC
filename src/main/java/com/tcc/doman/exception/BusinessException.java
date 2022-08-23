package com.tcc.doman.exception;

import com.tcc.api.exceptionhandler.ProblemType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;
    private final ProblemType problemType;

    public BusinessException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.code = "#";
        this.problemType = ProblemType.ERRO_NEGOCIO;
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
        this.status = HttpStatus.BAD_REQUEST;
        this.code = "#";
        this.problemType = ProblemType.ERRO_NEGOCIO;
    }
    public BusinessException(String message, HttpStatus status,ProblemType problemType) {
        super(message);
        this.code = "#";
        this.status = status;
        this.problemType = problemType;
    }

    public BusinessException(String message, String code, HttpStatus status,ProblemType problemType) {
        super(message);
        this.code = code;
        this.status = status;
        this.problemType = problemType;
    }

    public BusinessException(String message, String code, HttpStatus status,ProblemType problemType, Throwable throwable) {
        super(message,throwable);
        this.code = code;
        this.status = status;
        this.problemType = problemType;
    }


}
