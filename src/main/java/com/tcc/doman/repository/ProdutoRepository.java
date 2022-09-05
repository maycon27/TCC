package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.Produto;
import com.tcc.doman.repository.helper.ProdutoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>, ProdutoQuery {

    @Query("select new com.tcc.api.dto.NomeDTO(p.id,p.nome) from Produto p where lower(p.nome) like ?1")
    List<NomeDTO> findByNome(String filter);
}
