package com.tcc.api.dto;

import com.tcc.doman.model.enums.DiponivelIndisponivel;
import com.tcc.doman.model.enums.SimNao;
import com.tcc.doman.model.enums.UnidadeMedida;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {


    private Integer id;
    @NotNull
    @ApiModelProperty(example = "Carne")
    private String nome;
    @ApiModelProperty(example = "10")
    private Integer quantidadeEstoque;
    @NotNull
    @ApiModelProperty(example = "0")
    private BigDecimal preco;
    @ApiModelProperty(example = "sku exemplo")
    private String sku;
    @ApiModelProperty(example = "friboi")
    private String marca;
    @ApiModelProperty(example = "carne bovina")
    private String descricao;
    @ApiModelProperty(example = "QUILO")
    private UnidadeMedida unidadeMedida;
    @ApiModelProperty(example = "SIM")
    private SimNao ativo;
    @ApiModelProperty(example = "DISPONIVEL")
    private DiponivelIndisponivel situacao;
    @NotNull
    private NomeDTO categoriaProduto;
}
