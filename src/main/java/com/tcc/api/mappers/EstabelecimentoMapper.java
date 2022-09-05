package com.tcc.api.mappers;


import com.tcc.api.dto.EstabelecimentoDTO;
import com.tcc.doman.model.Estabelecimento;
import com.tcc.doman.model.TipoEstabelecimento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {

    EstabelecimentoDTO toModel(Estabelecimento estabelecimento);

    @InheritInverseConfiguration
    Estabelecimento toDomainObject(EstabelecimentoDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(EstabelecimentoDTO dto, @MappingTarget Estabelecimento estabelecimento);

    @BeforeMapping
    default void flushEntity(@MappingTarget Estabelecimento estabelecimento) {
        estabelecimento.setTipoEstabelecimento(new TipoEstabelecimento());
    }
}
