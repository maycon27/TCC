package com.tcc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResumDTO {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "jose@email.com")
    private String login;
    @ApiModelProperty(example = "jose")
    private String nome;

    public UsuarioResumDTO(){

    }
    public UsuarioResumDTO(Integer id, String login, String nome) {
        this.id = id;
        this.login = login;
        this.nome = nome;
    }
}
