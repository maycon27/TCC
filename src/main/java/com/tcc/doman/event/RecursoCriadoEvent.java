package com.tcc.doman.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Classe que contro ao evento ao ser criado um objeto devolvendo o id
 * Created by john on 20/10/17.
 */
public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1l;


    private final HttpServletResponse response;
    private final Integer codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Integer codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }


    public HttpServletResponse getResponse() {
        return response;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
