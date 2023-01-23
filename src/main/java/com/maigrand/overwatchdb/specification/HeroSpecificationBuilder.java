package com.maigrand.overwatchdb.specification;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.searchcriteria.HeroSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class HeroSpecificationBuilder implements SpecificationBuilder<Hero, HeroSearchCriteria> {
    @Override
    public Specification<Hero> build(HeroSearchCriteria searchCriteria) {
        Specification<Hero> specification = null;

        if (searchCriteria.getId() != null) {
            specification = filterById(searchCriteria.getId());
        }

        if (searchCriteria.getName() != null) {
            specification = filterByName(searchCriteria.getName());
        }

        if (searchCriteria.getRole() != null) {
            specification = filterByRole(searchCriteria.getRole());
        }

        return specification;
    }

    private Specification<Hero> filterById(Integer id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<Hero> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private Specification<Hero> filterByRole(Role role) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"), role);
    }
}
