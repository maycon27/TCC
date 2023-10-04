package com.tcc.api.resources.cadastro;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.resources.swagger.cadastro.UsuarioSwagger;
import com.tcc.doman.model.Usuario;
import com.tcc.doman.service.UsurioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource implements UsuarioSwagger {

    @Autowired
    private UsurioService service;
    @Override
    @GetMapping()
    public ResponseEntity<Usuario> recuperarUsuario() {
        Optional<Usuario> usuario = service.recuperarUsuarioLogado();
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
