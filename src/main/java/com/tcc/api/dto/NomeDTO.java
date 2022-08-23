package com.tcc.api.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NomeDTO {

    @ApiModelProperty(example = "3", required = true)
    @NotNull
    private Integer id;
    @ApiModelProperty(example = "nome", required = true)
    private String nome;

    public NomeDTO() {
    }

    public NomeDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
