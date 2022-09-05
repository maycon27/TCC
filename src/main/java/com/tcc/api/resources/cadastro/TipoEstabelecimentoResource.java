package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.resources.swagger.cadastro.TipoEstabelecimentoSwagger;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/cadastros/tipo-estabelecimento", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoEstabelecimentoResource implements TipoEstabelecimentoSwagger {

    @Autowired
    private TipoEstabelecimentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Override
    public List<TipoEstabelecimentoDTO> pesquisar() {
        return service.buscarTodos();
    }

    @Override
    @GetMapping("/nome")
    public List<NomeDTO> pesquisarPorNome(@RequestParam String filter) {
        return service.BuscarPorNome(filter);
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TipoEstabelecimentoDTO> pesquisarPorId(@PathVariable Integer id) {
        Optional<TipoEstabelecimentoDTO> tipoEstabelecimento = service.buscarPorIdDTO(id);
        return tipoEstabelecimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public ResponseEntity<TipoEstabelecimentoDTO> criar(@Valid @RequestBody TipoEstabelecimentoDTO dto, HttpServletResponse response) {
        TipoEstabelecimentoDTO tipoEstabelecimentoSalvo = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEstabelecimentoSalvo);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void editar(@PathVariable Integer id,@Valid @RequestBody TipoEstabelecimentoDTO dto) {
        service.atualizar(id,dto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }
}
