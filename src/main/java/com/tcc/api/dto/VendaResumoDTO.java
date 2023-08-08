package com.tcc.api.dto;

import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VendaResumoDTO {

    private Integer id;

    private LocalDate dataVenda;

    private BigDecimal valorTotal;

    private StatusVenda status;

    private SituacaoVenda situacao;

    private String NomeCliente;
}
