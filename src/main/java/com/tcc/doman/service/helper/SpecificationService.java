package com.tcc.doman.service.helper;


import com.tcc.doman.repository.helper.search.GenericSpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SpecificationService<T> {

    public Specification<T> getSpecificationFilter(String search) {

        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();

        Pattern pattern = Pattern.compile("(\\w+\\.\\w+|\\w+)(@|>:|<:|!IN|IN|!|:|<|>|~)(.+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<T> spec = builder.build();
        return spec;
    }

}
