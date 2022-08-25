package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento,Integer> {

    @Query("select new com.tcc.api.dto.NomeDTO(e.id,e.nome) from Estabelecimento e where lower(e.nome) like ?1")
    List<NomeDTO> findByNome(String nome);
}
