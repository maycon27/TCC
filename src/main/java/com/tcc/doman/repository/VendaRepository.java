package com.tcc.doman.repository;

import com.tcc.api.dto.VendaResuminda;
import com.tcc.api.dto.VendaResumoDTO;
import com.tcc.doman.model.Venda;
import com.tcc.doman.repository.helper.VendaQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Integer>, VendaQuery {

    @Query(value = "SELECT new com.tcc.api.dto.VendaResumoDTO(v.id, v.dataVenda, v.valorTotal,v.status, v.situacao, c.nome, p.nome, e.nome)" +
            " FROM Venda v " +
            " LEFT JOIN ItensVenda iv ON (v.id = iv.venda.id) " +
            " LEFT JOIN Produto p ON (iv.produto.id = p.id) " +
            " LEFT JOIN Estabelecimento e ON (iv.produto.estabelecimento.id = e.id) " +
            " LEFT JOIN Cliente c ON (c.id = v.cliente.id) " +
            " group by v.id, v.dataVenda, v.valorTotal,  v.situacao, c.nome, e.nome, p.nome " +
            " ORDER BY v.id")
    List<VendaResumoDTO> findVendaByClienteId(Integer idCliente);

    @Query(value = "SELECT new com.tcc.api.dto.VendaResuminda(v.id, v.dataVenda, v.valorTotal, e.nome, v.situacao, c.nome, p.nome)" +
            " FROM Venda v " +
            " LEFT JOIN ItensVenda iv ON (v.id = iv.venda.id) " +
            " LEFT JOIN Produto p ON (iv.produto.id = p.id) " +
            " LEFT JOIN Estabelecimento e ON (iv.produto.estabelecimento.id = e.id) " +
            " LEFT JOIN Cliente c ON (c.id = v.cliente.id) " +
            " group by v.id, v.dataVenda, v.valorTotal,  v.situacao, c.nome, e.nome, p.nome " +
            " ORDER BY v.id")
    List<VendaResuminda> consultarVendas();

    @Query(value = "SELECT new com.tcc.api.dto.VendaResuminda(v.id, v.dataVenda, v.valorTotal, e.nome, v.situacao, c.nome, p.nome)" +
            " FROM Venda v " +
            " LEFT JOIN ItensVenda iv ON (v.id = iv.venda.id) " +
            " LEFT JOIN Produto p ON (iv.produto.id = p.id) " +
            " LEFT JOIN Estabelecimento e ON (iv.produto.estabelecimento.id = e.id) " +
            " LEFT JOIN Cliente c ON (c.id = v.cliente.id) " +
            " group by v.id, v.dataVenda, v.valorTotal,  v.situacao, c.nome, e.nome, p.nome " +
            " ORDER BY e.nome")
    List<VendaResuminda> consultarVendasColetar();

}
