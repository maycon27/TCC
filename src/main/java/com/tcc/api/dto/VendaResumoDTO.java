package com.tcc.api.dto;

import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class VendaResumoDTO {

    private Integer id;

    private LocalDate dataVenda;

    private BigDecimal valorTotal;

    private StatusVenda status;

    private SituacaoVenda situacao;

    private String nomeCliente;

    private String nomeProduto;

    private String nomeEstabelecimento;


    public VendaResumoDTO(Integer id, LocalDate dataVenda, BigDecimal valorTotal, StatusVenda status, SituacaoVenda situacao,
                          String nomeCliente) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.status = status;
        this.situacao = situacao;
        this.nomeCliente = nomeCliente;
    }

    public VendaResumoDTO(Integer id, LocalDate dataVenda, BigDecimal valorTotal, StatusVenda status, SituacaoVenda situacao,
                          String nomeCliente, String nomeProduto, String nomeEstabelecimento) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.status = status;
        this.situacao = situacao;
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.nomeEstabelecimento = nomeEstabelecimento;
    }
}
