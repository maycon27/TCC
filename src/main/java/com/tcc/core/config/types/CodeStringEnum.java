package com.tcc.core.config.types;

public interface CodeStringEnum {

    String getCode();

    default String getPack() {
        return this.getClass().getName();
    }
}
