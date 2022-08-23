package com.tcc.doman.service;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.mappers.TipoEstabelecimentoMapper;
import com.tcc.doman.model.TipoEstabelecimento;
import com.tcc.doman.repository.TipoEstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoEstabelecimentoService {

    @Autowired
    private TipoEstabelecimentoRepository repository;

    @Autowired
    private TipoEstabelecimentoMapper mapper;


    public List<TipoEstabelecimentoDTO> buscarTodos(){
        var tipoEstabelecimento = repository.findAll();

        return tipoEstabelecimento.stream().map(v -> mapper.toModel(v)).collect(Collectors.toList());
    }

    @Transactional
    public TipoEstabelecimentoDTO criar(TipoEstabelecimentoDTO dto){
        var tipoEstabelecimento = mapper.toDomainObject(dto);
        repository.save(tipoEstabelecimento);
        dto.setId(tipoEstabelecimento.getId());
        return dto;
    }

    public List<NomeDTO> BuscarPorNome(String filter) {

        return repository.findByNome("%"+filter.toLowerCase()+"%");
    }

    @Transactional
    public void atualizar(Integer id, TipoEstabelecimentoDTO dto){
        var tipoEstabelecimento = findById(id);

        mapper.copyToDomainObject(dto,tipoEstabelecimento);
    }

    public Optional<TipoEstabelecimentoDTO> buscarPorIdDTO(Integer id){
        var tipoEstabelecimento = repository.findById(id);

        if(tipoEstabelecimento.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        var tipoEstabelecimentoOPT = Optional.of(mapper.toModel(tipoEstabelecimento.get()));
        return tipoEstabelecimentoOPT;
    }

    @Transactional
    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private TipoEstabelecimento findById(Integer id) {
        Optional<TipoEstabelecimento> tipoSalvo = repository.findById(id);
        if (tipoSalvo.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return tipoSalvo.get();
    }
}
