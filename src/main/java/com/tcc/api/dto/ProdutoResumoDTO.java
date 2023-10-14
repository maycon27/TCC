package com.tcc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoResumoDTO {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "arroz")
    private String nome;
    @ApiModelProperty(example = "alimentos")
    private String categoriaProduto;
    @ApiModelProperty(example = "grãos")
    private String descricao;
    @ApiModelProperty(example = "100.0")
    private BigDecimal preco;
    private String imagemProduto;

    public ProdutoResumoDTO(Integer id, String nome, String categoriaProduto, String descricao, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.categoriaProduto = categoriaProduto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public ProdutoResumoDTO(Integer id, String nome, String categoriaProduto, String descricao, BigDecimal preco, String imagemProduto) {
        this.id = id;
        this.nome = nome;
        this.categoriaProduto = categoriaProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemProduto = imagemProduto;
    }
}
