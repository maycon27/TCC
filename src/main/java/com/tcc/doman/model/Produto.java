package com.tcc.doman.model;


import com.tcc.doman.model.enums.DiponivelIndisponivel;
import com.tcc.doman.model.enums.SimNao;
import com.tcc.doman.model.enums.UnidadeMedida;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
@Data
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Size(max = 50)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "QUANTIDADE_ESTOQUE")
    private Integer quantidadeEstoque;
    @NotNull
    @Column(name = "PRECO")
    private BigDecimal preco;
    @Column(name = "SKU")
    @Size(max = 50)
    private String sku;
    @Column(name = "Marca")
    @Size(max = 50)
    private String marca;
    @Column(name = "DESCRICAO")
    @Size(max = 50)
    private String descricao;
    @Column(name = "UNIDADE_MEDIDA")
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private UnidadeMedida unidadeMedida;
    @Column(name = "ATIVO")
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private SimNao ativo;
    @Column(name = "SITUACAO")
    @Type(type = "com.tcc.core.config.types.CodeStringEnumType")
    private DiponivelIndisponivel situacao;
    @JoinColumn(name = "ID_CATEGORIA_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull
    private CategoriaProduto categoriaProduto;

    public Produto() {
    }
}
