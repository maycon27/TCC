package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.ProdutoDTO;
import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.exceptionhandler.Problem;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.core.swagger.ApiPageable;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Produto")
public interface ProdutoSwagger {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separar por virgular.",
                    name = "field", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa lista de produtos por especificação")
    @ApiPageable
    PagedModel<ProdutoResumoDTO> pesquisar(String search, Pageable pageable);

    @ApiOperation("Lista todos os produtos por nome")
    List<NomeDTO> findByNome(@ApiParam(value = "nome de um produto", example = "Arroz", required = true) String filter);

    @ApiOperation("Busca um produto por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da produto inválida", response = Problem.class),
            @ApiResponse(code = 404, message = "Produto não encontrada", response = Problem.class)
    })
    ResponseEntity<ProdutoDTO> findById(@ApiParam(value = "ID de um produto", example = "1", required = true) Integer id);

    @ApiOperation("Cria um produtos")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
    })
    ResponseEntity<ProdutoDTO> salvar(
            @ApiParam(name = "corpo", value = "Representação de um novo produtos", required = true) ProdutoDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza um produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID de um produto", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados",
                    required = true) ProdutoDTO dto);

    @ApiOperation("Exclui um produto por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto excluído"),
            @ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "ID de um produto", example = "1", required = true) Integer id);
}
