package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Estabelecimento")
public interface EstabelecimentoSwagger {

    @ApiOperation("Retorna todos os Estabelecimentos")
    List<EstabelecimentoDTO> pesquisar();

    @ApiOperation("Pesquisa todos os Estabelecimentos que contenha uma parte ou nome")
    List<NomeDTO> pesquisarPorNome(@ApiParam(value = "nome", example = "venda", required = true) String filter);

    @ApiOperation("Retrona um Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do Estabelecimento invalido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estabelecimento não encontrado", response = Problem.class)
    })
    ResponseEntity<EstabelecimentoDTO> pesquisarPorId(@ApiParam(value = "id", example = "1", required = true) Integer id);

    @ApiOperation("Cria um novo Estabelecimento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estabelecimento cadastrado"),
    })
    ResponseEntity<EstabelecimentoDTO> criar(
            @ApiParam(name = "corpo", value = "Representação de uma novo Estabelecimento", required = true) EstabelecimentoDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza um Estabelecimento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estabelecimento atualizado"),
            @ApiResponse(code = 404, message = "Estabelecimento não encontrado", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de um Estabelecimento com os novos dados",
                    required = true) EstabelecimentoDTO dto);

    @ApiOperation("Remove um Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estabelecimento excluido"),
            @ApiResponse(code = 404, message = "Estabelecimento não encontrado", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "id", example = "1", required = true) Integer id);
}
