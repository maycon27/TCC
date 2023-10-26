package com.tcc.api.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProdutoVendaDTO {

    private Integer idVenda;
    @NotNull
    private String nomeProduto;
    @NotNull
    private Integer quantidade;
    @NotNull
    private String nomeCliente;

    private BigDecimal valorTotal;
    private LocalDate dataVenda;

    public ProdutoVendaDTO(Integer idVenda, String nomeProduto, Integer quantidade, String nomeCliente, LocalDate dataVenda, BigDecimal valorTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.nomeCliente = nomeCliente;
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }
}
