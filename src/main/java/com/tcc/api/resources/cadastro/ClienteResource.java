package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.ClienteDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.resources.swagger.cadastro.ClienteSwagger;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.service.ClienteService;
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
@RequestMapping(value = "/cadastros/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource implements ClienteSwagger {

    @Autowired
    private ClienteService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Override
    public List<ClienteDTO> pesquisar() {
        return service.buscarTodos();
    }

    @GetMapping("/nome")
    @Override
    public List<NomeDTO> pesquisarPorNome(@RequestParam String filter) {
        return service.BuscarPorNome(filter);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ClienteDTO> pesquisarPorId(@PathVariable Integer id) {

        Optional<ClienteDTO> cliente = service.buscarPorIdDTO(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO dto, HttpServletResponse response) {
        ClienteDTO clienteSalvo = service.criar(dto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void editar(@PathVariable Integer id,@Valid @RequestBody ClienteDTO dto) {
        service.atualizar(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deletar(@PathVariable Integer id) {
        service.excluir(id);
    }
}
