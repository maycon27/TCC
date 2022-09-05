package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.api.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Tipo Estabelecimento")
public interface TipoEstabelecimentoSwagger {

    @ApiOperation("Retorna todos os Tipos Estabelecimentos")
    List<TipoEstabelecimentoDTO> pesquisar();

    @ApiOperation("Pesquisa todos os Tipos Estabelecimentos que contenha uma parte ou nome")
    List<NomeDTO> pesquisarPorNome(@ApiParam(value = "nome", example = "venda", required = true) String filter);

    @ApiOperation("Retrona um Tipo Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do Tipo Estabelecimento invalido", response = Problem.class),
            @ApiResponse(code = 404, message = "Tipo Estabelecimento não encontrado", response = Problem.class)
    })
    ResponseEntity<TipoEstabelecimentoDTO> pesquisarPorId(@ApiParam(value = "id", example = "1", required = true) Integer id);

    @ApiOperation("Cria um novo Tipo Estabelecimento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Tipo Estabelecimento cadastrado"),
    })
    ResponseEntity<TipoEstabelecimentoDTO> criar(
            @ApiParam(name = "corpo", value = "Representação de uma novo Tipo Estabelecimento", required = true) TipoEstabelecimentoDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza um Tipo Estabelecimento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Tipo Estabelecimento atualizado"),
            @ApiResponse(code = 404, message = "Tipo Estabelecimento não encontrado", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de um Tipo Estabelecimento com os novos dados",
                    required = true) TipoEstabelecimentoDTO dto);

    @ApiOperation("Remove um Tipo Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Tipo Estabelecimento excluido"),
            @ApiResponse(code = 404, message = "Tipo Estabelecimento não encontrado", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "id", example = "1", required = true) Integer id);
}
