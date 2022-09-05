package com.tcc.doman.listener;


import com.tcc.doman.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;


@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Integer codigo = recursoCriadoEvent.getCodigo();

        adicionarHeaderLocation(response, codigo);
    }

    @EventListener
    public void handleResponseLocationHeader(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Integer codigoRecurso = event.getCodigo();

        URI locationURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(codigoRecurso)
                .toUri();

        response.addHeader("Location", locationURI.toASCIIString());
    }

    private void adicionarHeaderLocation(HttpServletResponse response, Integer codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
