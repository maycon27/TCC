package com.tcc.doman.service;

import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.UsuarioResumDTO;
import com.tcc.api.mappers.EstabelecimentoMapper;
import com.tcc.doman.model.Estabelecimento;
import com.tcc.doman.model.Usuario;
import com.tcc.doman.repository.EstabelecimentoRepository;
import com.tcc.doman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    @Autowired
    private EstabelecimentoMapper mapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EstabelecimentoDTO> buscarTodos(){
        var estabelecimento = repository.findAll();

        return estabelecimento.stream().map(v -> mapper.toModel(v)).collect(Collectors.toList());
    }

    @Transactional
    public EstabelecimentoDTO criar(EstabelecimentoDTO dto){
        var estabelecimento = mapper.toDomainObject(dto);
        var usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setLogin(dto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(dto.getSenha()));
        usuario.setRole("Estabelecimento");
        var usuarioSalvo = usuarioRepository.save(usuario);

        estabelecimento.setUsuario(new Usuario());
        estabelecimento.getUsuario().setId(usuarioSalvo.getId());
        repository.save(estabelecimento);
        dto.setUsuario(new UsuarioResumDTO());
        dto.setId(estabelecimento.getId());
        dto.getUsuario().setNome(usuarioSalvo.getNome());
        dto.getUsuario().setLogin(usuario.getLogin());
        dto.getUsuario().setId(usuario.getId());
        return dto;
    }
    public List<NomeDTO> BuscarPorNome(String filter) {

        return repository.findByNome("%"+filter.toLowerCase()+"%");
    }

    public NomeDTO BuscarPorUsuario(Integer idUsuario) {

        return repository.findByUsuario(idUsuario);
    }

    @Transactional
    public void atualizar(Integer id, EstabelecimentoDTO dto){
        Estabelecimento estabelecimento = findById(id);

        mapper.copyToDomainObject(dto,estabelecimento);
    }

    public Optional<EstabelecimentoDTO> buscarPorIdDTO(Integer id){
        var estabelecimento = repository.findById(id);

        if(estabelecimento.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        var estabelecimentoOPT = Optional.of(mapper.toModel(estabelecimento.get()));
        return estabelecimentoOPT;
    }

    @Transactional
    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private Estabelecimento findById(Integer id) {
        Optional<Estabelecimento> estabelecimentoSalvo = repository.findById(id);
        if (estabelecimentoSalvo.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return estabelecimentoSalvo.get();
    }

}
