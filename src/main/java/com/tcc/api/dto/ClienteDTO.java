package com.tcc.api.dto;

import com.tcc.doman.model.Usuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;
    @NotNull
    @ApiModelProperty(example = "Maycon Douglas")
    private String nome;
    @NotNull
    @ApiModelProperty(example = "03457599947")
    private String cpf;
    @NotNull
    @ApiModelProperty(example = "maycon@hotmail.com")
    private String email;
    @ApiModelProperty(example = "79996367565")
    private String telefone;
    @ApiModelProperty(example = "nenhum")
    private String logradouro;
    @ApiModelProperty(example = "17")
    private String numero;
    @ApiModelProperty(example = "nenhum")
    private String complemento;
    @ApiModelProperty(example = "centro")
    private String bairro;
    @ApiModelProperty(example = "48580000")
    private String cep;
    @ApiModelProperty(example = "Areia Branca")
    private String nomeMunicipio;
    @ApiModelProperty(example = "SE")
    private String uf;
    @ApiModelProperty(example = "Brasil")
    private String nomePais;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "1997-03-01")
    private LocalDate dataNascimento;
    @ApiModelProperty(example = "12345678")
    private String senha;
    private UsuarioResumDTO usuario;
}
