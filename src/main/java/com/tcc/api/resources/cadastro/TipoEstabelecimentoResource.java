package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.TipoEstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cadastros/tipo-estabelecimento", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoEstabelecimentoResource {

    @Autowired
    private TipoEstabelecimentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<TipoEstabelecimentoDTO> pesquisar() {
        return service.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<TipoEstabelecimentoDTO> criar(@Valid @RequestBody TipoEstabelecimentoDTO dto, HttpServletResponse response) {
        TipoEstabelecimentoDTO motoristaSalvo = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(motoristaSalvo);
    }
}
