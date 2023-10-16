package com.tcc.api.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVendaDTO {
    @NotNull
    private String nomeProduto;
    @NotNull
    private Long quantidade;
    @NotNull
    private String nomeCliente;

    public ProdutoVendaDTO(String nomeProduto, Long quantidade, String nomeCliente) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.nomeCliente = nomeCliente;
    }
}
