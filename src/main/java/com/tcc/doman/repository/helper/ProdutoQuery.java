package com.tcc.doman.repository.helper;

import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.resources.swagger.model.PagedModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoQuery {
    PagedModel<ProdutoResumoDTO> findAll(String search, Pageable pageable);

}
