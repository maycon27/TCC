package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.ProdutoDTO;
import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.resources.swagger.cadastro.ProdutoSwagger;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cadastros/produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoRecource implements ProdutoSwagger {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Override
    public PagedModel<ProdutoResumoDTO> pesquisar(@RequestParam(required = false, value = "search", defaultValue = "") String search,
                                                 @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {

        return service.findAll(search,pageable);
    }
    @Override
    @GetMapping("/nome")
    public List<NomeDTO> findByNome(@RequestParam String filter) {
        return service.findByNome(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id){
        Optional<ProdutoDTO> produto = service.buscarPorIdDTO(id);
        return  produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoDTO dto, HttpServletResponse response) {
        ProdutoDTO produtoSalvo = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    @Override
    public void editar(@PathVariable Integer id,@Valid @RequestBody ProdutoDTO dto) {
        service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){ service.excluir(id);}
}
