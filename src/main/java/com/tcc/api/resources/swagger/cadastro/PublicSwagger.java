package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.ClienteDTO;
import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.api.dto.ProdutoDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "End Points Publicos")
public interface PublicSwagger {

    @ApiOperation("Cria um Cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente cadastrado"),
    })
    ResponseEntity<ClienteDTO> salvarCliente(
            @ApiParam(name = "corpo", value = "Representação de um novo Cliente", required = true) ClienteDTO dto,
            HttpServletResponse response);

    @ApiOperation("Cria um Estabelecimento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estabelecimento cadastrado"),
    })
    ResponseEntity<EstabelecimentoDTO> salvarEstabelecimento(
            @ApiParam(name = "corpo", value = "Representação de um novo Estabelecimento", required = true) EstabelecimentoDTO dto,
            HttpServletResponse response);
}
