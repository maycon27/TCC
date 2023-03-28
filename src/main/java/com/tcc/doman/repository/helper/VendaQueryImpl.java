package com.tcc.doman.repository.helper;

import com.tcc.api.dto.VendaResumoDTO;
import com.tcc.api.resources.swagger.model.PagedModel;
import com.tcc.doman.model.*;
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

public class VendaQueryImpl implements  VendaQuery{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SpecificationService<Venda> specificationService;
    @Override
    public PagedModel<VendaResumoDTO> findAll(String search, Pageable pageable) {
        Specification<Venda> specificationFilter = specificationService.getSpecificationFilter(search);

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<VendaResumoDTO> criteria = builder.createQuery(VendaResumoDTO.class);

        Root<Venda> root = criteria.from(Venda.class);

        Join<Venda, Cliente> vendaClienteJoin = root.join(Venda_.CLIENTE, JoinType.INNER);

        if (specificationFilter != null) {

            Predicate predicate = specificationFilter.toPredicate(root, criteria, builder);

            criteria.where(predicate);
        }

        criteria.select(builder.construct(VendaResumoDTO.class,
                root.get(Venda_.id),
                root.get(Venda_.dataVenda),
                root.get(Venda_.valorTotal),
                root.get(Venda_.status),
                root.get(Venda_.situacao),
                vendaClienteJoin.get(Cliente_.nome)
        ));

        ordenarRegistros(criteria, builder, root, pageable);

        TypedQuery<VendaResumoDTO> query = em.createQuery(criteria);

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

    private Long total(Specification<Venda> specification) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Venda> root = criteria.from(Venda.class);

        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, criteria, builder);
            criteria.where(predicate);
        }

        criteria.select(builder.count(root));
        return em.createQuery(criteria).getSingleResult();
    }
}
