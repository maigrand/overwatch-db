package com.maigrand.overwatchdb.specification;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.searchcriteria.AimTypeSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AimTypeSpecificationBuilder implements SpecificationBuilder<AimType, AimTypeSearchCriteria> {

    @Override
    public Specification<AimType> build(AimTypeSearchCriteria searchCriteria) {
        Specification<AimType> specification = null;

        if (searchCriteria.getId() != null) {
            specification = filterById(searchCriteria.getId());
        }

        if (searchCriteria.getType() != null) {
            specification = filterByType(searchCriteria.getType());
        }

        return specification;
    }

    private Specification<AimType> filterById(Integer id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<AimType> filterByType(String type) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + type + "%");
    }
}
