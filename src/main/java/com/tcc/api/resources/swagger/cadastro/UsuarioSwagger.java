package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.dto.UsuarioDTO;
import com.tcc.api.exceptionhandler.Problem;
import com.tcc.doman.model.Usuario;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(tags = "Usuario")
public interface UsuarioSwagger {

    @ApiOperation("Retorna O usuario logado")
    ResponseEntity<Usuario> recuperarUsuario();
}
