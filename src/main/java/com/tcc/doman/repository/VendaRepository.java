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
            " LEFT JOIN ItensVenda iv ON (v.id = iv.venda.id) " +
            " LEFT JOIN Produto p ON (iv.produto.id = p.id) " +
            " LEFT JOIN Estabelecimento e ON (iv.produto.estabelecimento.id = e.id) " +
            " LEFT JOIN Cliente c ON (c.id = v.cliente.id) " +
            " group by v.id, v.dataVenda, v.valorTotal,  v.situacao, c.nome, e.nome")
    List<VendaResuminda> consultarVendas();

}
