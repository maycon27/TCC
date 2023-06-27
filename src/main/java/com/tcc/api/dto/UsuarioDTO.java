package com.tcc.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "jose",required = true)
    @NotNull
    @Length(max = 60)
    private String nome;
    @ApiModelProperty(example = "jose@email.com",required = true)
    @NotNull
    @Length(max = 60)
    private String login;
    @ApiModelProperty(example = "12345678",required = true)
    @NotNull
    @Length(max = 60)
    private String senha;

    private String role;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
