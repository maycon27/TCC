package com.tcc.doman.repository;

import com.tcc.doman.model.Venda;
import com.tcc.doman.repository.helper.VendaQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda,Integer>, VendaQuery {
}
