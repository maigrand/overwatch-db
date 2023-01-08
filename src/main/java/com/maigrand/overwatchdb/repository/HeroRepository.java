package com.maigrand.overwatchdb.repository;

import com.maigrand.overwatchdb.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
}
