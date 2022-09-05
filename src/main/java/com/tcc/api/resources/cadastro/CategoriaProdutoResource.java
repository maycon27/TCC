package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.CategoriaProdutoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.resources.swagger.cadastro.CategoriaProdutoSwagger;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.CategoriaProdutoService;
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
@RequestMapping(value = "/cadastros/categoria-produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaProdutoResource implements CategoriaProdutoSwagger {


    @Autowired
    private CategoriaProdutoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Override
    public List<CategoriaProdutoDTO> pesquisar() {
        return service.buscarTodos();
    }

    @Override
    @GetMapping("/nome")
    public List<NomeDTO> pesquisarPorNome(@RequestParam String filter) {
        return service.BuscarPorNome(filter);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> pesquisarPorId(@PathVariable Integer id) {
        Optional<CategoriaProdutoDTO> categoriaProduto = service.buscarPorIdDTO(id);
        return categoriaProduto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoriaProdutoDTO> criar(@Valid @RequestBody CategoriaProdutoDTO dto, HttpServletResponse response) {
        CategoriaProdutoDTO categoriaProdutoSalvo = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProdutoSalvo);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @Valid @RequestBody CategoriaProdutoDTO dto) {
        service.atualizar(id, dto);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }
}
