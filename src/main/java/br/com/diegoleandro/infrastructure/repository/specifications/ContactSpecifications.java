package br.com.diegoleandro.infrastructure.repository.specifications;

import br.com.diegoleandro.api.domain.entity.Contact;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactSpecifications implements Specification<Contact> {

    private static final long serialVersionUID = 1L;

    public String nome;
    public String contato;

    public ContactSpecifications(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    @Override
    public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.nome)) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + this.nome + "%"));
        }
        if (Objects.nonNull(this.contato)) {
            predicates.add(criteriaBuilder.equal(root.get("contato"), this.contato));
        }
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));

    }
}
