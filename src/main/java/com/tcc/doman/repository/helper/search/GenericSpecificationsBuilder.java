package com.tcc.doman.repository.helper.search;


import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecificationsBuilder<T> {

    private final List<Filtro> filtros;

    public GenericSpecificationsBuilder() {
        this.filtros = new ArrayList<>();
    }


    public GenericSpecificationsBuilder<T> with(String atributo, String operation, Object value) {

        OperadorRelacional operadorRelacional = OperadorRelacional.findSpecifications(operation);

        Object[] values = value.toString().split(";");

        filtros.add(new Filtro(atributo, operadorRelacional, values.length > 1 ? values : value));
        return this;
    }

    public GenericSpecificationsBuilder<T> with(String atributo, String operation, Object[] values) {

        OperadorRelacional operadorRelacional = OperadorRelacional.findSpecifications(operation);

        filtros.add(new Filtro(atributo, operadorRelacional, values));
        return this;
    }

    public Specification<T> build() {

        if (filtros.size() == 0) {
            return null;
        }

        List<Specification<T>> specs = new ArrayList<>();
        filtros.forEach(f -> specs.add(new GenericSpecification(f)));

        Specification<T> result = specs.get(0);
        for (int i = 0; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
