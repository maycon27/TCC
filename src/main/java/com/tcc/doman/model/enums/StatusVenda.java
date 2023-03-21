package com.tcc.doman.model.enums;

import com.tcc.core.config.types.CodeStringEnum;

public enum StatusVenda implements CodeStringEnum {

    PREPARAR("PREPARAR"),
    SEPARADO("SEPRADO"),
    COLETADO("COLETADO"),
    ENTREGUE("ENTREGUE");

    private final String code;

    StatusVenda(String code) {
        this.code = code;
    }

    public static StatusVenda fromCode(String code) {
        for (StatusVenda statusVenda : StatusVenda.values()) {
            if (statusVenda.getCode().equals(code)) {
                return statusVenda;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }

    @Override
    public String getCode() {
        return code;
    }
}
