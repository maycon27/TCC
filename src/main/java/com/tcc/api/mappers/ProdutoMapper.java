package com.tcc.api.mappers;


import com.tcc.api.dto.ProdutoDTO;
import com.tcc.doman.model.CategoriaProduto;
import com.tcc.doman.model.Produto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toModel(Produto estabelecimento);

    @InheritInverseConfiguration
    Produto toDomainObject(ProdutoDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(ProdutoDTO dto, @MappingTarget Produto estabelecimento);

    @BeforeMapping
    default void flushEntity(@MappingTarget Produto produto) {
        produto.setCategoriaProduto(new CategoriaProduto());
    }
}
