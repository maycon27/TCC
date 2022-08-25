package com.tcc.api.dto;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class TipoEstabelecimentoDTO {

    private Integer id;
    @NotNull
    @ApiModelProperty(example = "SuperMercado", required = true)
    @Size(max = 50)
    private String nome;

   public TipoEstabelecimentoDTO(){}

}
