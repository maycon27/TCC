package com.tcc.api.mappers;

import com.tcc.api.dto.CategoriaProdutoDTO;
import com.tcc.doman.model.CategoriaProduto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoriaProdutoMapper {

    CategoriaProdutoDTO toModel(CategoriaProduto categoriaProduto);

    @InheritInverseConfiguration
    CategoriaProduto toDomainObject(CategoriaProdutoDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(CategoriaProdutoDTO dto, @MappingTarget CategoriaProduto categoriaProduto);
}
