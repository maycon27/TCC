package com.tcc.doman.model.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.tcc.core.config.types.CodeStringEnum;

public enum DiponivelIndisponivel implements CodeStringEnum {

    DISPONIVEL("D"),
    INDISPONIVEL("I");

    private final String code;

    DiponivelIndisponivel(String code) {
        this.code = code;
    }
    public static DiponivelIndisponivel fromCode(String code) {
        for (DiponivelIndisponivel diponivelIndisponivel : DiponivelIndisponivel.values()) {
            if (diponivelIndisponivel.getCode().equals(code)) {
                return diponivelIndisponivel;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported for enum!");
    }

    @JsonCreator
    static DiponivelIndisponivel fromValue(String value) {
        for (DiponivelIndisponivel diponivelIndisponivel : DiponivelIndisponivel.values()) {
            if (diponivelIndisponivel.getCode().equals(value) || diponivelIndisponivel.toString().equals(value)) {
                return diponivelIndisponivel;
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
