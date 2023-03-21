package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.VendaDTO;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cadastros/venda", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendaResource {

    @Autowired
    private VendaService service;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping
    public ResponseEntity<VendaDTO> criar(@Valid @RequestBody VendaDTO dto, HttpServletResponse response) {
        VendaDTO vendaSalva = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Integer id){
        var venda = service.buscarPorId(id);
        return  venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
