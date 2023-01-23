package com.maigrand.overwatchdb.specification;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.searchcriteria.RoleSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleSpecificationBuilder implements SpecificationBuilder<Role, RoleSearchCriteria> {
    @Override
    public Specification<Role> build(RoleSearchCriteria searchCriteria) {
        Specification<Role> specification = null;

        if (searchCriteria.getId() != null) {
            specification = filterById(searchCriteria.getId());
        }

        if (searchCriteria.getName() != null) {
            specification = filterByName(searchCriteria.getName());
        }

        return specification;
    }

    private Specification<Role> filterById(Integer id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<Role> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
