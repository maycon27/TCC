package com.tcc.api.mappers;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.doman.model.TipoEstabelecimento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoEstabelecimentoMapper {
    TipoEstabelecimentoDTO toModel(TipoEstabelecimento tipoEstabelecimento);

    @InheritInverseConfiguration
    TipoEstabelecimento toDomainObject(TipoEstabelecimentoDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(TipoEstabelecimentoDTO dto, @MappingTarget TipoEstabelecimento tipoEstabelecimento);
}
