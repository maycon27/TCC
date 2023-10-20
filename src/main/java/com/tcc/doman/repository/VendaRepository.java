package com.tcc.doman.repository;

import com.tcc.api.dto.VendaResuminda;
import com.tcc.doman.model.Venda;
import com.tcc.doman.repository.helper.VendaQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Integer>, VendaQuery {

    List<Venda> findVendaByClienteId(Integer idCliente);

    @Query(value = "SELECT new com.tcc.api.dto.VendaResuminda(v.id, v.dataVenda, v.valorTotal, e.nome, v.situacao, c.nome)" +
            " FROM Venda v " +
            " INNER JOIN ItensVenda iv ON (v.id = iv.produto.id) " +
            " INNER JOIN Produto p ON (iv.produto.id = p.id) " +
            " INNER JOIN Estabelecimento e ON (p.estabelecimento.id = e.id) " +
            " INNER JOIN Cliente c ON (c.id = v.cliente.id)")
    List<VendaResuminda> consultarVendas();

}
