package com.tcc.doman.repository.helper.search;


import com.tcc.core.config.types.CodeStringEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenericSpecification<T> implements Specification<T> {


    private final Filtro filtro;
    private Object[] atributos;
    private Class<? extends Object> tipo;

    public GenericSpecification(Filtro filtro) {
        this.filtro = filtro;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        tipo = getJavaType(root, filtro.getAtributo());
        switch (filtro.getOperadorRelacional()) {
            case MAIOR:
                return maior(root, builder);
            case MAIOR_OU_IGUAL:
                return maiorOuIgual(root, builder);
            case MENOR:
                return menor(root, builder);
            case MENOR_OU_IGUAL:
                return menorOuIgual(root, builder);
            case DIFERENTE:
                return diferente(root, builder);
            case LIKE:
                return contains(root, builder);
            case IN:
                return in(root);
            case NOT_IN:
                return notIn(root);
            case IGUAL:
                return igual(root, builder);
        }

        return null;
    }

    private Predicate in(Root<T> root) {
        var list = (List<Object>) getValueObject();
        return getProperty(root).in(list);
    }

    private Predicate notIn(Root<T> root) {
        return getProperty(root).in(getValueObject()).not();
    }

    private Predicate igual(Root<T> root, CriteriaBuilder builder) {
        return builder.equal(getProperty(root), getValueObject());
    }

    private Predicate contains(Root<T> root, CriteriaBuilder builder) {
        return tipo == String.class ? contem(root, builder) : builder.equal(getProperty(root), getValueObject());
    }


    private Predicate diferente(Root<T> root, CriteriaBuilder builder) {
        return tipo == String.class ? naoContem(root, builder) : builder.notEqual(getProperty(root), getValueObject());
    }

    private Predicate contem(Root<T> root, CriteriaBuilder builder) {
        return builder.like(builder.lower(getProperty(root)), getValue());
    }

    private Predicate naoContem(Root<T> root, CriteriaBuilder builder) {
        return builder.notLike(getProperty(root), getValue());
    }

    private Predicate menor(Root<T> root, CriteriaBuilder builder) {

        if (LocalDate.class.equals(tipo)) {
            return builder.lessThan(root.get(filtro.getAtributo()), getValueDate());
        } else if (LocalDateTime.class.equals(tipo)) {
            return builder.lessThan(root.get(filtro.getAtributo()), getValueDateTime());
        }
        return builder.lessThan(getProperty(root), getValue());
    }

    private Predicate menorOuIgual(Root<T> root, CriteriaBuilder builder) {

        if (LocalDate.class.equals(tipo)) {
            return builder.lessThanOrEqualTo(root.get(filtro.getAtributo()), getValueDate());
        } else if (LocalDateTime.class.equals(tipo)) {
            return builder.lessThanOrEqualTo(root.get(filtro.getAtributo()), getValueDateTime());
        }
        return builder.lessThanOrEqualTo(getProperty(root), getValue());
    }

    private Predicate maior(Root<T> root, CriteriaBuilder builder) {

        if (LocalDate.class.equals(tipo)) {
            return builder.greaterThan(root.get(filtro.getAtributo()), getValueDate());
        } else if (LocalDateTime.class.equals(tipo)) {
            return builder.greaterThan(root.get(filtro.getAtributo()), getValueDateTime());
        }
        return builder.greaterThan(getProperty(root), getValue());
    }

    private Predicate maiorOuIgual(Root<T> root, CriteriaBuilder builder) {
        if (LocalDate.class.equals(tipo)) {
            return builder.greaterThanOrEqualTo(root.get(filtro.getAtributo()), getValueDate());
        } else if (LocalDateTime.class.equals(tipo)) {
            return builder.greaterThanOrEqualTo(root.get(filtro.getAtributo()), getValueDateTime());
        }
        return builder.greaterThanOrEqualTo(getProperty(root), getValue());
    }

    private Path<String> getProperty(Root<T> root) {

        String[] keys = filtro.getAtributo().split("_");
        switch (keys.length) {
            case 1:
                return root.get(keys[0]);
            case 2:
                return root.get(keys[0]).get(keys[1]);
            case 3:
                return root.get(keys[0]).get(keys[1]).get(keys[2]);
            case 4:
                return root.get(keys[0]).get(keys[1]).get(keys[2]).get(keys[3]);
            default:
                return root.get(filtro.getAtributo());
        }


    }

    private Class<? extends Object> getJavaType(Root<T> root, String key) {
        String[] keys = key.split("_");
        switch (keys.length) {
            case 1:
                return root.get(keys[0]).getJavaType();
            case 2:
                return root.get(keys[0]).get(keys[1]).getJavaType();
            case 3:
                return root.get(keys[0]).get(keys[1]).get(keys[2]).getJavaType();
            case 4:
                return root.get(keys[0]).get(keys[1]).get(keys[2]).get(keys[3]).getJavaType();
            default:
                return root.get(key).getJavaType();
        }

    }

    private String getValue() {
        return (tipo == String.class && filtro.getOperadorRelacional() == OperadorRelacional.LIKE) ? "%" + filtro.getValor().toString().toLowerCase() + "%" : filtro.getValor().toString();
    }

    private LocalDate getValueDate() {
        return LocalDate.parse(getValue());
    }

    private LocalDateTime getValueDateTime() {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(getValue(), DATEFORMATTER);
        return ldt;
    }


    private Object getValueObject() {
        if (tipo == LocalDate.class) {
            return getValueDate();
        } else if (tipo == LocalDateTime.class) {
            return getValueDateTime();
        } else if (tipo == Boolean.class) {
            return Boolean.parseBoolean(getValue());
        } else if (tipo.getSuperclass() == Enum.class && isValueEnumOrString()) {
            return getEnumValue((Class<T>) tipo);
        } else if (tipo.getSuperclass() == String.class && filtro.getValor() instanceof String[]) {
            var list = Arrays.asList((String[]) filtro.getValor());
            return list;
        } else if (tipo.getSuperclass() == Number.class && filtro.getValor() instanceof String[]) {
            var list = Arrays.stream((String[]) filtro.getValor())
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            return list;
        } else if (tipo.getSuperclass() == Enum.class && filtro.getValor() instanceof String[]) {
            var list = getEnumValues((Class<T>) tipo);
            return list;
        }


        return getValue();
    }

    private boolean isValueEnumOrString() {
        return filtro.getValor() == Enum.class || filtro.getValor() instanceof String;
    }

    private Object getEnumValue(Class<T> enumType) {
        for (T c : enumType.getEnumConstants()) {

            if (c instanceof CodeStringEnum && ((CodeStringEnum) c).getCode().equals(getValue())) {
                return c;
            } else if (c.toString().equals(getValue())) {
                return c;
            }
        }
        return null;
    }

    private List<Object> getEnumValues(Class<T> enumType) {


        if (enumType.isEnum()) {

            var enun = Arrays.stream(enumType.getEnumConstants()).findFirst();

            if (enun.isPresent() && enun.get() instanceof CodeStringEnum) {

                List<CodeStringEnum> values = (List<CodeStringEnum>) Arrays.stream(enumType.getEnumConstants()).collect(Collectors.toList());
                List<String> valuesFitler = Arrays.asList((String[]) filtro.getValor());

                return values
                        .stream()
                        .filter(c -> valuesFitler.contains(c.getCode()) || valuesFitler.contains(c.toString())).collect(Collectors.toList());
            }

        }
        return null;
    }
}
