package com.tcc.api.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstabelecimentoDTO {

    private Integer id;
    @NotNull
    @ApiModelProperty(example = "SuperMercado Messias Peixoto")
    private String nome;
    @NotNull
    @ApiModelProperty(example = "SuperMercado Messias Peixoto",required = true)
    private String nomeFantasia;
    @ApiModelProperty(example = "72236455000111",required = true)
    private String cnpj;
    @NotNull
    @ApiModelProperty(example = "messiaspeixoto@hotmail.com",required = true)
    private String email;
    @ApiModelProperty(example = "079999558844")
    private String telefone;
    @ApiModelProperty(example = "proximo ao mercado de carne")
    private String logradouro;
    @ApiModelProperty(example = "100")
    private String numero;
    @ApiModelProperty(example = "nenhum")
    private String complemento;
    @ApiModelProperty(example = "centro")
    private String bairro;
    @ApiModelProperty(example = "49500-684")
    private String cep;
    @ApiModelProperty(example = "Itabaiana")
    private String nomeMunicipio;
    @ApiModelProperty(example = "SE")
    private String uf;
    @ApiModelProperty(example = "Brasil")
    private String nomePais;
    @NotNull
    private NomeDTO tipoEstabelecimento;
}
