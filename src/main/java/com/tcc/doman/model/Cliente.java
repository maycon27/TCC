package com.tcc.doman.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CLIENTES")
@Data
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Size(max = 50)
    @Column(name = "NOME")
    private String nome;
    @NotNull
    @Size(max = 20)
    @Column(name = "CPF")
    private String cpf;
    @NotNull
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 14)
    @Column(name = "TELEFONE")
    private String telefone;
    @Size(max = 50)
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Size(max = 10)
    @Column(name = "NUMERO")
    private String numero;
    @Size(max = 50)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Size(max = 50)
    @Column(name = "BAIRRO")
    private String bairro;
    @Size(max = 8)
    @Column(name = "CEP")
    private String cep;
    @Size(max = 50)
    @Column(name = "NOME_MUNICIPIO")
    private String nomeMunicipio;
    @Size(max = 2)
    @Column(name = "UF")
    private String uf;
    @Size(max = 50)
    @Column(name = "NOME_PAIS")
    private String nomePais;
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull
    private Usuario usuario;
}
