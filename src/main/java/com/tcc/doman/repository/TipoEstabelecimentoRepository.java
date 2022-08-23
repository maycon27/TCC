package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.TipoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoEstabelecimentoRepository extends JpaRepository<TipoEstabelecimento,Integer> {

    @Query("select new com.tcc.api.dto.NomeDTO(t.id,t.nome) from TipoEstabelecimento t where lower(t.nome) like ?1")
    List<NomeDTO>findByNome(String nome);
}
