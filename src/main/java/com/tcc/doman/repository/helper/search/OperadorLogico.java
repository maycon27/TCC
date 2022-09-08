package com.tcc.doman.repository.helper.search;

public enum OperadorLogico {

    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    NOT_NULL("IS NOT NULL"),
    NOT_IN("NOT IN");


    private final String descricao;

    OperadorLogico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
