package br.com.diegoleandro.infrastructure.repository.specifications;

import br.com.diegoleandro.api.domain.entity.Professional;
import br.com.diegoleandro.api.domain.entity.enums.CargoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ProfessionalSpecifications implements Specification<Professional> {

    private static final long serialVersionUID = 1L;

    public String nome;
    public CargoEnum cargo;
    public LocalDate nascimento;

    public ProfessionalSpecifications(String nome, CargoEnum cargo, LocalDate nascimento) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
    }


    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.nome)) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + this.nome + "%"));
        }
        if (Objects.nonNull(this.cargo)) {
            predicates.add(criteriaBuilder.equal(root.get("cargo"), this.cargo));
        }
        if (Objects.nonNull(this.nascimento)) {
            java.sql.Date nascimentoSqlDate = java.sql.Date.valueOf(this.nascimento);

            Expression<java.sql.Date> nascimentoField = root.get("nascimento");
            Expression<LocalDate> nascimentoDate = criteriaBuilder.function("date", LocalDate.class, nascimentoField);

            predicates.add(criteriaBuilder.equal(nascimentoDate, nascimentoSqlDate));
        }
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));

    }
}
