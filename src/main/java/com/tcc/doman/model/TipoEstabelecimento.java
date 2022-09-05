package com.tcc.doman.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TIPO_ESTABELECIMENTO")
@Data
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoEstabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Size(max = 50)
    @Column(name = "NOME")
    private String nome;

}
