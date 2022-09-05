package com.tcc.doman.service;

import com.tcc.api.dto.CategoriaProdutoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.mappers.CategoriaProdutoMapper;
import com.tcc.doman.model.CategoriaProduto;
import com.tcc.doman.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository repository;

    @Autowired
    private CategoriaProdutoMapper mapper;


    public List<CategoriaProdutoDTO> buscarTodos(){
        var categoriaProduto = repository.findAll();

        return categoriaProduto.stream().map(v -> mapper.toModel(v)).collect(Collectors.toList());
    }

    @Transactional
    public CategoriaProdutoDTO criar(CategoriaProdutoDTO dto){
        var categoriaProduto = mapper.toDomainObject(dto);
        repository.save(categoriaProduto);
        dto.setId(categoriaProduto.getId());
        return dto;
    }

    public List<NomeDTO> BuscarPorNome(String filter) {

        return repository.findByNome("%"+filter.toLowerCase()+"%");
    }

    @Transactional
    public void atualizar(Integer id, CategoriaProdutoDTO dto){
        var categoriaProduto = findById(id);

        mapper.copyToDomainObject(dto,categoriaProduto);
    }

    public Optional<CategoriaProdutoDTO> buscarPorIdDTO(Integer id){
        var categoriaProduto = repository.findById(id);

        if(categoriaProduto.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        var tipoEstabelecimentoOPT = Optional.of(mapper.toModel(categoriaProduto.get()));
        return tipoEstabelecimentoOPT;
    }

    @Transactional
    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private CategoriaProduto findById(Integer id) {
        Optional<CategoriaProduto> categoriaSalvo = repository.findById(id);
        if (categoriaSalvo.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return categoriaSalvo.get();
    }
}
