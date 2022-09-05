package com.tcc.doman.service;


import com.tcc.api.dto.ClienteDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.mappers.ClienteMapper;
import com.tcc.doman.model.Cliente;
import com.tcc.doman.repository.ClienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRespository repository;

    @Autowired
    private ClienteMapper mapper;

    public List<ClienteDTO> buscarTodos(){
        var cliente = repository.findAll();

        return cliente.stream().map(v -> mapper.toModel(v)).collect(Collectors.toList());
    }

    @Transactional
    public ClienteDTO criar(ClienteDTO dto){
        var cliente = mapper.toDomainObject(dto);
        repository.save(cliente);
        dto.setId(cliente.getId());
        return dto;
    }

    public List<NomeDTO> BuscarPorNome(String filter) {

        return repository.findByNome("%"+filter.toLowerCase()+"%");
    }

    @Transactional
    public void atualizar(Integer id, ClienteDTO dto){
        Cliente cliente = findById(id);

        mapper.copyToDomainObject(dto,cliente);
    }

    public Optional<ClienteDTO> buscarPorIdDTO(Integer id){
        var cliente = repository.findById(id);

        if(cliente.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        var clienteOPT = Optional.of(mapper.toModel(cliente.get()));
        return clienteOPT;
    }

    @Transactional
    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private Cliente findById(Integer id) {
        Optional<Cliente> clienteSalvo = repository.findById(id);
        if (clienteSalvo.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return clienteSalvo.get();
    }


}
