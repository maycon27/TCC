package com.tcc.api.dto;

import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import com.tcc.doman.model.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VendaDTO {

    private Integer id;

    private LocalDate dataVenda;

    @NotNull
    private TipoPagamento tipoPagamento;

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    private StatusVenda status;

    @NotNull
    private SituacaoVenda situacao;

    @NotNull
    private NomeDTO cliente;

    private List<ItensVendaDTO> itens;
}
