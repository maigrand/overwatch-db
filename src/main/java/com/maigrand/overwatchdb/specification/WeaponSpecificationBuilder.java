package com.maigrand.overwatchdb.specification;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.searchcriteria.WeaponSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class WeaponSpecificationBuilder implements SpecificationBuilder<Weapon, WeaponSearchCriteria> {

    @Override
    public Specification<Weapon> build(WeaponSearchCriteria searchCriteria) {
        Specification<Weapon> specification = null;

        if (searchCriteria.getId() != null) {
            specification = filterById(searchCriteria.getId());
        }

        if (searchCriteria.getName() != null) {
            specification = specification != null
                    ? specification.and(filterByName(searchCriteria.getName()))
                    : filterByName(searchCriteria.getName());
        }

        if (searchCriteria.getUsage() != null) {
            specification = specification != null
                    ? specification.and(filterByUsage(searchCriteria.getUsage()))
                    : filterByName(searchCriteria.getUsage());
        }

        if (searchCriteria.getHeadshot() != null) {
            specification = specification != null
                    ? specification.and(filterByHeadshot(searchCriteria.getHeadshot()))
                    : filterByHeadshot(searchCriteria.getHeadshot());
        }

        return specification;
    }

    private Specification<Weapon> filterById(Integer id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private Specification<Weapon> filterByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private Specification<Weapon> filterByUsage(String usage) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("usage"), "%" + usage + "%");
    }

    private Specification<Weapon> filterByHeadshot(boolean headshot) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("headshot"), headshot);
    }
}
