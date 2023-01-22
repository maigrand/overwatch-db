package com.maigrand.overwatchdb.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, S> {

    Specification<T> build(S searchCriteria);
}
