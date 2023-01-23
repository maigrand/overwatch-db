package com.maigrand.overwatchdb.repository;

import com.maigrand.overwatchdb.entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Integer>, JpaSpecificationExecutor<Ability> {
}
