package com.maigrand.overwatchdb.repository;

import com.maigrand.overwatchdb.entity.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
}
