package com.tcc.api.mappers;

import com.tcc.api.dto.ClienteDTO;
import com.tcc.doman.model.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteMapper {


    ClienteDTO toModel(Cliente cliente);

    @InheritInverseConfiguration
    Cliente toDomainObject(ClienteDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(ClienteDTO dto, @MappingTarget Cliente cliente);
}
