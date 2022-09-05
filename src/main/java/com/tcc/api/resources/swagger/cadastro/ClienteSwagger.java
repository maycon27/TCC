package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.ClienteDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Cliente")
public interface ClienteSwagger {
    @ApiOperation("Retorna todos os clientes")
    List<ClienteDTO> pesquisar();

    @ApiOperation("Pesquisa todos os clientes que contenha uma parte ou nome")
    List<NomeDTO> pesquisarPorNome(@ApiParam(value = "nome", example = "venda", required = true) String filter);

    @ApiOperation("Retrona um Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do cliente invalido", response = Problem.class),
            @ApiResponse(code = 404, message = "cliente não encontrado", response = Problem.class)
    })
    ResponseEntity<ClienteDTO> pesquisarPorId(@ApiParam(value = "id", example = "1", required = true) Integer id);

    @ApiOperation("Cria um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "cliente cadastrado"),
    })
    ResponseEntity<ClienteDTO> criar(
            @ApiParam(name = "corpo", value = "Representação de uma novo cliente", required = true) ClienteDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "cliente atualizado"),
            @ApiResponse(code = 404, message = "cliente não encontrado", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de um cliente com os novos dados",
                    required = true) ClienteDTO dto);

    @ApiOperation("Remove um cliente por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "cliente excluido"),
            @ApiResponse(code = 404, message = "cliente não encontrado", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "id", example = "1", required = true) Integer id);
}
