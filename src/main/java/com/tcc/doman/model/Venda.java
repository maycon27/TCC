package com.tcc.doman.model;

import com.tcc.doman.model.enums.SituacaoVenda;
import com.tcc.doman.model.enums.StatusVenda;
import com.tcc.doman.model.enums.TipoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "VENDA")
@Data
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATA_VENDA")
    private LocalDate dataVenda;

    @Column(name = "TIPO_PAGAMENTO")
    @NotNull
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private TipoPagamento tipoPagamento;

    @Column(name = "VALOR_TOTAL")
    @NotNull
    private BigDecimal valorTotal;

    @Column(name = "STATUS")
    @NotNull
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private StatusVenda status;

    @Column(name = "SITUACAO")
    @NotNull
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private SituacaoVenda situacao;

    @JoinColumn(name = "ID_CLIENTES", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItensVenda> itens;


    public Venda(){

    }
}
