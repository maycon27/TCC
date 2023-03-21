package com.tcc.doman.service;

import com.tcc.api.dto.VendaDTO;
import com.tcc.api.mappers.VendaMapper;
import com.tcc.doman.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private VendaMapper mapper;

    @Transactional
    public VendaDTO criar(VendaDTO dto){
        var venda = mapper.toDomainObject(dto);

        venda = repository.save(venda);

        dto.setId(venda.getId());

        return dto;
    }

    public Optional<VendaDTO> buscarPorId(Integer id){
        var vendaSalva = repository.findById(id);

        if(vendaSalva.isEmpty()){
            return Optional.empty();
        }

        var vendaOPT = Optional.of(mapper.toModel(vendaSalva.get()));
        return vendaOPT;
    }
}
