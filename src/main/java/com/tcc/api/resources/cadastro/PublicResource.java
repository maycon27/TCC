package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.ClienteDTO;
import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.resources.swagger.cadastro.PublicSwagger;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.ClienteService;
import com.tcc.doman.service.EstabelecimentoService;
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
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicResource implements PublicSwagger {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private TipoEstabelecimentoService tipoEstabelecimentoService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Override
    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO dto, HttpServletResponse response) {
        ClienteDTO clienteSalvo = clienteService.criar(dto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @Override
    @PostMapping("/estabelecimento")
    public ResponseEntity<EstabelecimentoDTO> salvarEstabelecimento(@Valid @RequestBody EstabelecimentoDTO dto, HttpServletResponse response) {
        EstabelecimentoDTO estabelecimentoSalvo = estabelecimentoService.criar(dto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoSalvo);
    }

    @Override
    @GetMapping("/nome")
    public List<NomeDTO> pesquisarPorNome(@RequestParam String filter) {
        return tipoEstabelecimentoService.BuscarPorNome(filter);
    }

}
