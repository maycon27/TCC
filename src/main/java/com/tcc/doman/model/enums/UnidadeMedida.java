package com.tcc.doman.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tcc.core.config.types.CodeStringEnum;

public enum UnidadeMedida implements CodeStringEnum {
    UNIDADE("UN"),
    QUILO("KG"),
    CAIXA("CX"),
    METRO("MT"),
    METRO_QUADRADO("M2"),
    LITRO("LT"),
    GRAMA("GR"),
    METRO_CUBICO("M3"),
    JOGO("JG"),
    ROLO("RL"),
    CENTIMETRO("CM"),
    SACO("SC");

    private final String code;

    UnidadeMedida(String code) {
        this.code = code;
    }
    public static UnidadeMedida fromCode(String code) {
        for (UnidadeMedida unidadeMedida : UnidadeMedida.values()) {
            if (unidadeMedida.getCode().equals(code)) {
                return unidadeMedida;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported for enum!");
    }

    @JsonCreator
    static UnidadeMedida fromValue(String value) {
        for (UnidadeMedida unidadeMedida : UnidadeMedida.values()) {
            if (unidadeMedida.getCode().equals(value) || unidadeMedida.toString().equals(value)) {
                return unidadeMedida;
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
