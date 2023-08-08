package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.*;
import com.tcc.api.exceptionhandler.Problem;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.core.swagger.ApiPageable;
import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "Venda")
public interface VendaSwagger {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separar por virgular.",
                    name = "field", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa lista vendas por especificação")
    @ApiPageable
    PagedModel<VendaResumoDTO> pesquisar(String search, Pageable pageable);


    @ApiOperation("Busca uma venda por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da venda inválida", response = Problem.class),
            @ApiResponse(code = 404, message = "venda não encontrada", response = Problem.class)
    })
    ResponseEntity<VendaDTO> findById(@ApiParam(value = "ID de uma venda", example = "1", required = true) Integer id);

    @ApiOperation("Cria uma venda")
    @ApiResponses({
            @ApiResponse(code = 201, message = "venda cadastrada"),
    })
    ResponseEntity<VendaDTO> salvar(
            @ApiParam(name = "corpo", value = "Representação de um nova venda", required = true) VendaDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza uma venda")
    @ApiResponses({
            @ApiResponse(code = 200, message = "venda atualizada"),
            @ApiResponse(code = 404, message = "venda não encontrada", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID de um venda", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de um venda com os novos dados",
                    required = true) VendaDTO dto);

    @ApiOperation("Exclui um venda por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "venda excluído"),
            @ApiResponse(code = 404, message = "venda não encontrado", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "ID de um venda", example = "1", required = true) Integer id);

    void alterarStatusVenda(@ApiParam(value = "ID de um venda", example = "1", required = true) Integer id, StatusVenda statusVenda);

    void alterarSituacaoVenda(@ApiParam(value = "ID de um venda", example = "1", required = true)Integer id, @RequestParam SituacaoVenda situacaoVenda);
}
