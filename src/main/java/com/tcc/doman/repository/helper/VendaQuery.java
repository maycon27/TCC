package com.tcc.doman.repository.helper;

import com.tcc.api.dto.VendaResumoDTO;
import com.tcc.api.resources.swagger.model.PagedModel;
import org.springframework.data.domain.Pageable;

public interface VendaQuery {

    PagedModel<VendaResumoDTO> findAll(String search, Pageable pageable);
}
