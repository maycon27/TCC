package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRespository extends JpaRepository<Cliente,Integer> {

    @Query("select new com.tcc.api.dto.NomeDTO(c.id,c.nome) from Cliente c where lower(c.nome) like ?1")
    List<NomeDTO> findByNome(String nome);

    @Query("select new com.tcc.api.dto.NomeDTO(c.id,c.nome) from Cliente c where c.usuario.id = ?1")
    NomeDTO findByUsuario(Integer IdUsuario);
}
