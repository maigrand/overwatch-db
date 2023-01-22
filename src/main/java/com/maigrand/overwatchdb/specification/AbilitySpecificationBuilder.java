package com.maigrand.overwatchdb.specification;


import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.searchcriteria.AbilitySearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AbilitySpecificationBuilder implements SpecificationBuilder<Ability, AbilitySearchCriteria> {

    @Override
    public Specification<Ability> build(AbilitySearchCriteria searchCriteria) {
        Specification<Ability> specification = null;

        if (searchCriteria.getId() != null) {
            specification = filterById(searchCriteria.getId());
        }

        if (searchCriteria.getName() != null) {
            specification = filterByName(searchCriteria.getName());
        }

        return specification;
    }

    private Specification<Ability> filterById(Integer id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<Ability> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
