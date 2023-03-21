package com.tcc.api.mappers;

import com.tcc.api.dto.VendaDTO;
import com.tcc.doman.model.Venda;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    VendaDTO toModel(Venda venda);

    @InheritInverseConfiguration
    Venda toDomainObject(VendaDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void copyToDomainObject(VendaDTO dto, @MappingTarget Venda estabelecimento);

    @AfterMapping
    default void popularRelacionamento(@MappingTarget Venda venda) {
        venda.getItens().forEach(itens -> {
            itens.setVenda(venda);
        });
    }

}
