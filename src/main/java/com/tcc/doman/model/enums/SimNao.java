package com.tcc.doman.model.enums;


import com.tcc.core.config.types.CodeStringEnum;

public enum SimNao implements CodeStringEnum {

    SIM("S"),
    NAO("N");

    private final String code;

    SimNao(String code) {
        this.code = code;
    }

    public static SimNao fromCode(String code) {
        for (SimNao simNao : SimNao.values()) {
            if (simNao.getCode().equals(code)) {
                return simNao;
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
