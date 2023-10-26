package com.tcc.doman.service;

import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.dto.VendaDTO;
import com.tcc.api.dto.VendaResuminda;
import com.tcc.api.dto.VendaResumoDTO;
import com.tcc.api.mappers.VendaMapper;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.model.Venda;
import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import com.tcc.doman.repository.ProdutoRepository;
import com.tcc.doman.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaMapper mapper;

    public PagedModel<VendaResumoDTO> findAll(String search , Pageable pageable){
        return repository.findAll(search,pageable);
    }

    public List<VendaResuminda> consultarVendas(){
        return repository.consultarVendas();
    }

    public List<VendaResuminda> consultarVendasColetar(){
        return repository.consultarVendasColetar();
    }

    @Transactional
    public VendaDTO criar(VendaDTO dto){
        var venda = mapper.toDomainObject(dto);

        dto.getItens().forEach(item -> {
            try {
                verificarEstoque(item.getProduto().getId(), item.getQuantidade());
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });

        venda = repository.save(venda);

        dto.setId(venda.getId());

        return dto;
    }

    @Transactional
    public void atualizar(Integer id, VendaDTO dto){

        var vendaSalva = findById(id);

        mapper.copyToDomainObject(dto, vendaSalva);

        repository.save(vendaSalva);
    }

    public List<VendaResumoDTO> consultarVendaCliente(Integer idCliente){
        var venda = repository.findVendaByClienteId(idCliente);

        return  venda;
    }

    public Optional<VendaDTO> buscarPorId(Integer id){
        var vendaSalva = repository.findById(id);

        if(vendaSalva.isEmpty()){
            return Optional.empty();
        }

        var vendaOPT = Optional.of(mapper.toModel(vendaSalva.get()));
        return vendaOPT;
    }

    @Transactional
    public void excluir(Integer id){ repository.deleteById(id);}

    @Transactional
    public  void alterarStatusVenda(StatusVenda statusVenda, Integer id){
        var vendaSalva = findById(id);

        vendaSalva.setStatus(statusVenda);

        repository.save(vendaSalva);

    }

    @Transactional
    public  void alterarSituacaoVenda(SituacaoVenda situacaoVenda, Integer id){
        var vendaSalva = findById(id);

        vendaSalva.setSituacao(situacaoVenda);

        repository.save(vendaSalva);

    }

    private Venda findById(Integer id){
        Optional<Venda> vendaSalva = repository.findById(id);
        if(vendaSalva.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return vendaSalva.get();
    }

    private void verificarEstoque(Integer idProduto, Integer quantidadeVenda) throws ValidationException {
        var produtoSalvo = produtoRepository.findById(idProduto);

        if(quantidadeVenda > produtoSalvo.get().getQuantidadeEstoque()){
            throw new ValidationException("Quantidade em estoque é insuficiente!");
        }

        else {
            produtoSalvo.get().setQuantidadeEstoque(produtoSalvo.get().getQuantidadeEstoque() - quantidadeVenda);
            produtoRepository.save(produtoSalvo.get());
        }
    }
}
