package com.tcc.doman.service;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.doman.model.Usuario;
import com.tcc.doman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsurioService {

    @Autowired
    private UsuarioRepository repository;

    private Integer USUARIO_LAGADO;


    public void recuperarId(Integer id) {
        USUARIO_LAGADO = id;
    }

    public Optional<Usuario> recuperarUsuarioLogado(){
        var usuario = repository.findById(USUARIO_LAGADO);

        return usuario;
    }
}
