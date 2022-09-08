package com.tcc.doman.repository.helper;

import com.tcc.api.dto.ProdutoResumoDTO;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.model.CategoriaProduto;
import com.tcc.doman.model.CategoriaProduto_;
import com.tcc.doman.model.Produto;
import com.tcc.doman.model.Produto_;
import com.tcc.doman.service.helper.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class ProdutoQueryImpl implements ProdutoQuery {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SpecificationService<Produto> specificationService;

    @Override
    public PagedModel<ProdutoResumoDTO> findAll(String search, Pageable pageable) {
        Specification<Produto> specificationFilter = specificationService.getSpecificationFilter(search);

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProdutoResumoDTO> criteria = builder.createQuery(ProdutoResumoDTO.class);

        Root<Produto> root = criteria.from(Produto.class);

        Join<Produto, CategoriaProduto> categoriaProdutoJoin = root.join(Produto_.CATEGORIA_PRODUTO, JoinType.INNER);

        if (specificationFilter != null) {

            Predicate predicate = specificationFilter.toPredicate(root, criteria, builder);

            criteria.where(predicate);
        }
        criteria.select(builder.construct(ProdutoResumoDTO.class,
                root.get(Produto_.id),
                root.get(Produto_.nome),
                categoriaProdutoJoin.get(CategoriaProduto_.nome),
                root.get(Produto_.descricao),
                root.get(Produto_.preco)
        ));

        ordenarRegistros(criteria, builder, root, pageable);

        TypedQuery<ProdutoResumoDTO> query = em.createQuery(criteria);

        adicionarRestricoesDePaginacao(query, pageable);

        var page = new PageImpl<>(query.getResultList(), pageable, total(specificationFilter));

        return new PagedModel<>(page, query.getResultList());
    }

    private void ordenarRegistros(CriteriaQuery<?> criteria, CriteriaBuilder builder, Root<?> root, Pageable pageable) {

        Order ord;

        Sort sort = pageable.getSort();
        Sort.Order order = sort.iterator().next();
        String property = order.getProperty();

        var path = getProperty(root, property);

        ord = order.isAscending() ? builder.asc(path) : builder.desc(path);
        criteria.orderBy(ord);

    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Path<String> getProperty(Root<?> root, String atributo) {

        String[] keys = atributo.split("_");
        switch (keys.length) {
            case 1:
                return root.get(keys[0]);
            case 2:
                return root.get(keys[0]).get(keys[1]);
            case 3:
                return root.get(keys[0]).get(keys[1]).get(keys[2]);
            case 4:
                return root.get(keys[0]).get(keys[1]).get(keys[2]).get(keys[3]);
            default:
                return root.get(atributo);
        }

    }

    private Long total(Specification<Produto> specification) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Produto> root = criteria.from(Produto.class);

        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, criteria, builder);
            criteria.where(predicate);
        }

        criteria.select(builder.count(root));
        return em.createQuery(criteria).getSingleResult();
    }
}
