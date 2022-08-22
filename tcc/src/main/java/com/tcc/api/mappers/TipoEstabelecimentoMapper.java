package com.tcc.api.mappers;

import com.tcc.api.dto.TipoEstabelecimentoDTO;
import com.tcc.doman.model.TipoEstabelecimento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TipoEstabelecimentoMapper {

    TipoEstabelecimentoDTO toModel(TipoEstabelecimento tipoEstabelecimento);

    @InheritInverseConfiguration
    TipoEstabelecimento toDomainObject(TipoEstabelecimentoDTO dto);

    void copyToDomainObject(TipoEstabelecimentoDTO dto, @MappingTarget TipoEstabelecimento tipoEstabelecimento);
}
