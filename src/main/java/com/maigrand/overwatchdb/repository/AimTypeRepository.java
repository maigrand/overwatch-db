package com.maigrand.overwatchdb.repository;

import com.maigrand.overwatchdb.entity.AimType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AimTypeRepository extends JpaRepository<AimType, Integer>, JpaSpecificationExecutor<AimType> {
}
