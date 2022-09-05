package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto,Integer> {

    @Query("select new com.tcc.api.dto.NomeDTO(c.id,c.nome) from CategoriaProduto c where lower(c.nome) like ?1")
    List<NomeDTO> findByNome(String nome);
}
