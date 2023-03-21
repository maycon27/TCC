package com.tcc.doman.model.enums;
import com.tcc.core.config.types.CodeStringEnum;
public enum SituacaoVenda implements CodeStringEnum {

    ATIVO("ATIVO"),
    CANCELADO("CANCELADO");

    private final String code;

    SituacaoVenda(String code) {
        this.code = code;
    }

    public static SituacaoVenda fromCode(String code) {
        for (SituacaoVenda situacaoVenda : SituacaoVenda.values()) {
            if (situacaoVenda.getCode().equals(code)) {
                return situacaoVenda;
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
