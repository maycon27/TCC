package com.tcc.doman.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tcc.core.config.types.CodeStringEnum;

public enum TipoPagamento implements CodeStringEnum {

    DINHEIRO("DINHEIRO"),
    CARTAO_CREDITO("CARTAO CREDITO"),
    CARTAO_DEBITO("CARTAO DEBITO"),
    VALE_ALIMENTACAO("VALE ALIMENTACAO"),
    VALE_REFEICAO("VALE REFEICAO"),
    BOLETO_BANCARIO("BOLETO BANCARIO"),
    OUTROS("OUTROS");

    private final String code;

    TipoPagamento(String code) {
        this.code = code;
    }

    public static TipoPagamento fromCode(String code) {
        for (TipoPagamento f : TipoPagamento.values()) {
            if (f.getCode().equals(code)) {
                return f;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported for enum!");
    }

    @JsonCreator
    static TipoPagamento fromValue(String value) {
        for (TipoPagamento f : TipoPagamento.values()) {
            if (f.getCode().equals(value) || f.toString().equals(value)) {
                return f;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + value + " is not supported for enum!");
    }

    @Override
    public String getCode() {
        return code;
    }
}
