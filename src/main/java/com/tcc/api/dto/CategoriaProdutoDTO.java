package com.tcc.api.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoriaProdutoDTO {

    private Integer id;
    @NotNull
    @ApiModelProperty(example = "Alimentos", required = true)
    @Size(max = 50)
    private String nome;

    public CategoriaProdutoDTO(){}
}
