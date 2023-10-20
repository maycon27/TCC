package com.tcc.api.dto;

import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class VendaResuminda {

    private Integer id;

    private LocalDate dataVenda;

    private BigDecimal valorTotal;

    private String  nomeEstabelecimento;

    private SituacaoVenda situacao;

    private String nomeCliente;

    public VendaResuminda(Integer id, LocalDate dataVenda, BigDecimal valorTotal, String nomeEstabelecimento, SituacaoVenda situacao, String nomeCliente) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.situacao = situacao;
        this.nomeCliente = nomeCliente;
    }
}
