package com.tcc.api.resources.swagger.cadastro;

import com.tcc.api.dto.CategoriaProdutoDTO;
import com.tcc.api.dto.NomeDTO;
import com.tcc.api.exceptionhandler.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Categoria Produto")
public interface CategoriaProdutoSwagger {

    @ApiOperation("Retorna todos as Categorias de Produtos")
    List<CategoriaProdutoDTO> pesquisar();

    @ApiOperation("Pesquisa todos as Categorias de Produtos que contenha uma parte ou nome")
    List<NomeDTO> pesquisarPorNome(@ApiParam(value = "nome", example = "venda", required = true) String filter);

    @ApiOperation("Retrona um Tipo Estabelecimento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da Categoria Produto invalida", response = Problem.class),
            @ApiResponse(code = 404, message = "Categoria Produto não encontrada", response = Problem.class)
    })
    ResponseEntity<CategoriaProdutoDTO> pesquisarPorId(@ApiParam(value = "id", example = "1", required = true) Integer id);

    @ApiOperation("Cria uma nova Categoria Produto")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Categoria Produto cadastrada"),
    })
    ResponseEntity<CategoriaProdutoDTO> criar(
            @ApiParam(name = "corpo", value = "Representação de uma nova Categoria Produto", required = true) CategoriaProdutoDTO dto,
            HttpServletResponse response);

    @ApiOperation("Atualiza uma Categoria Produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Categoria Produto atualizada"),
            @ApiResponse(code = 404, message = "Categoria Produto não encontrada", response = Problem.class)
    })
    void editar(
            @ApiParam(value = "ID", example = "1", required = true) Integer id,
            @ApiParam(name = "corpo", value = "Representação de uma Categoria Produto com os novos dados",
                    required = true) CategoriaProdutoDTO dto);

    @ApiOperation("Remove uma Categoria Produto por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Categoria Produto excluida"),
            @ApiResponse(code = 404, message = "Categoria Produto não encontrada", response = Problem.class)
    })
    void deletar(
            @ApiParam(value = "id", example = "1", required = true) Integer id);
}