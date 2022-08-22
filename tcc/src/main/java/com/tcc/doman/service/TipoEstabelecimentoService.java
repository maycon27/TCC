package com.tcc.doman.service;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.mappers.TipoEstabelecimentoMapper;
import com.tcc.doman.model.TipoEstabelecimento;
import com.tcc.doman.repository.TipoEstabelecimentoRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoEstabelecimentoService {

    @Autowired
    private TipoEstabelecimentoRepository repository;

    @Autowired
    private TipoEstabelecimentoMapper mapper;

    public List<TipoEstabelecimentoDTO> buscarTodos(){
        List<TipoEstabelecimento> tipoEstabelecimento = repository.findAll();

        return tipoEstabelecimento.stream().map(v -> mapper.toModel(v)).collect(Collectors.toList());
    }

    @Transactional
    public TipoEstabelecimentoDTO criar(TipoEstabelecimentoDTO dto){
        TipoEstabelecimento tipoEstabelecimento = mapper.toDomainObject(dto);
        repository.save(tipoEstabelecimento);
        dto.setId(tipoEstabelecimento.getId());
        return dto;
    }
}
