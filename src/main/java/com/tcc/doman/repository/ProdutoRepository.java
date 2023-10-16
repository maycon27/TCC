package com.tcc.doman.repository;

import com.tcc.api.dto.NomeDTO;
import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.dto.ProdutoVendaDTO;
import com.tcc.doman.model.Produto;
import com.tcc.doman.repository.helper.ProdutoQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>, ProdutoQuery {

    @Query("select new com.tcc.api.dto.NomeDTO(p.id,p.nome) from Produto p where lower(p.nome) like ?1")
    List<NomeDTO> findByNome(String filter);

    List<Produto> findByEstabelecimentoId(Integer id);

    @Query(value = "select new  com.tcc.api.dto.ProdutoVendaDTO(p.nome, sum(iv.quantidade), c.nome) " +
            " from Produto p  inner join ItensVenda iv on (p.id = iv.produto.id) " +
            " inner join Venda  v on (iv.venda.id = v.id) " +
            " inner join Cliente c on (c.id = v.cliente.id) " +
            " where p.estabelecimento.id = :idEstabelecimento " +
            " group by p.nome,c.nome")
    List<ProdutoVendaDTO> consultarProdutosVendidos(Integer idEstabelecimento);
}
