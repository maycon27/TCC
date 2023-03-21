package com.tcc.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItensVendaDTO {
    private Integer id;
    @NotNull
    private Integer quantidade;
    @NotNull
    private NomeDTO produto;
}
