package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.payload.HeroDetails;
import com.maigrand.overwatchdb.repository.HeroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    private final RoleService roleService;

    public HeroService(HeroRepository heroRepository, RoleService roleService) {
        this.roleService = roleService;
        this.heroRepository = heroRepository;
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Hero findById(Integer id) {
        return heroRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Hero create(HeroDetails details) {
        Hero hero = new Hero();
        hero.setName(details.getName());
        hero.setRole(roleService.findById(details.getRoleId()));

        return heroRepository.save(hero);
    }
}
