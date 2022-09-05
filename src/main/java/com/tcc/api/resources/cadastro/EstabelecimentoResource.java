package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.resources.swagger.cadastro.EstabelecimentoSwagger;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cadastros/estabelecimento", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstabelecimentoResource implements EstabelecimentoSwagger {

    @Autowired
    private EstabelecimentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Override
    public List<EstabelecimentoDTO> pesquisar() {
        return service.buscarTodos();
    }

    @GetMapping("/nome")
    @Override
    public List<NomeDTO> pesquisarPorNome(@RequestParam String filter) {
        return service.BuscarPorNome(filter);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<EstabelecimentoDTO> pesquisarPorId(@PathVariable Integer id) {
        Optional<EstabelecimentoDTO> estabelecimento = service.buscarPorIdDTO(id);
        return estabelecimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public ResponseEntity<EstabelecimentoDTO> criar(@Valid @RequestBody EstabelecimentoDTO dto, HttpServletResponse response) {
        EstabelecimentoDTO estabelecimentoSalvo = service.criar(dto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoSalvo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void editar(@PathVariable Integer id,@Valid @RequestBody EstabelecimentoDTO dto) {
        service.atualizar(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }
}
