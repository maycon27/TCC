package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.*;
import com.tcc.api.resources.swagger.cadastro.VendaSwagger;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.event.RecursoCriadoEvent;
import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import com.tcc.doman.service.VendaService;
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

@RestController
@RequestMapping(value = "/cadastros/venda", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendaResource implements VendaSwagger {

    @Autowired
    private VendaService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public PagedModel<VendaResumoDTO> pesquisar(@RequestParam(required = false, value = "search", defaultValue = "") String search,
                                                @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return service.findAll(search,pageable);
    }

    @GetMapping("cliente/{id}")
    public List<VendaResumoDTO> findByEstabelecimento(@PathVariable Integer id){
        return service.consultarVendaCliente(id);

    }

    @GetMapping("consultar")
    public List<VendaResuminda> consultar(){
        return service.consultarVendas();
    }


    @PostMapping
    public ResponseEntity<VendaDTO> salvar(@Valid @RequestBody VendaDTO dto, HttpServletResponse response) {
        VendaDTO vendaSalva = service.criar(dto);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, dto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Integer id){
        var venda = service.buscarPorId(id);
        return  venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable Integer id,@Valid @RequestBody VendaDTO dto) {
        service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){ service.excluir(id);}

    @PutMapping("statusVenda")
    public  void alterarStatusVenda(@RequestParam Integer id, @RequestParam StatusVenda statusVenda){
        service.alterarStatusVenda(statusVenda, id);
    }

    @PutMapping("situacaoVenda/{id}")
    public  void alterarSituacaoVenda(@PathVariable Integer id, @RequestParam SituacaoVenda situacaoVenda){
        service.alterarSituacaoVenda(situacaoVenda, id);
    }
}
