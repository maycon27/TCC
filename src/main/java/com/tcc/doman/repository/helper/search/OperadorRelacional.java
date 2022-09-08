package com.tcc.doman.repository.helper.search;

public enum OperadorRelacional {

    IGUAL("="),
    LIKE("LIKE"),
    DIFERENTE("<>"),
    MENOR("<"),
    MENOR_OU_IGUAL("<="),
    MAIOR(">"),
    MAIOR_OU_IGUAL(">="),
    IN("IN"),
    NOT_IN("NOT IN");


    private final String descricao;

    OperadorRelacional(String descricao) {
        this.descricao = descricao;
    }

    public static OperadorRelacional find(String descricao) {
        for (final OperadorRelacional operador : OperadorRelacional.values()) {
            if (operador.getDescricao().equals(descricao)) {
                return operador;
            }
        }
        throw new IllegalArgumentException(String.format("Operador  %s%n não definido.", descricao));
    }

    public static OperadorRelacional findSpecifications(String descricao) {
        switch (descricao) {

            case ":":
                return OperadorRelacional.IGUAL;
            case "!":
                return OperadorRelacional.DIFERENTE;
            case "<:":
                return OperadorRelacional.MENOR_OU_IGUAL;
            case ">:":
                return OperadorRelacional.MAIOR_OU_IGUAL;
            case "<":
                return OperadorRelacional.MENOR;
            case ">":
                return OperadorRelacional.MAIOR;
            case "~":
                return OperadorRelacional.LIKE;
            case "IN":
                return OperadorRelacional.IN;
            case "!IN":
                return OperadorRelacional.NOT_IN;

        }
        throw new IllegalArgumentException(String.format("Operador  %s%n não definido.", descricao));
    }

    public String getDescricao() {
        return descricao;
    }
}
