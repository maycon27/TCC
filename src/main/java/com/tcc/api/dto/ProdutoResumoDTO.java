package com.tcc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class ProdutoResumoDTO {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "arroz")
    private String nome;
    private String categoriaProduto;
    private String descricao;
    @ApiModelProperty(example = "100.0")
    private BigDecimal preco;


}
