package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.HeroDetails;
import com.maigrand.overwatchdb.repository.HeroRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
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
                .orElseThrow(() -> new EntityNotFoundException("Hero not found"));
    }

    @Validated(OnCreate.class)
    public Hero create(HeroDetails details) {
        Hero hero = new Hero();
        hero.setName(details.getName());
        hero.setRole(roleService.findById(details.getRoleId()));

        return heroRepository.save(hero);
    }

    @Validated(OnUpdate.class)
    public Hero update(Integer id, HeroDetails details) {
        Hero hero = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(hero::setName);
        Optional.ofNullable(details.getRoleId()).ifPresent((roleId) -> {
            Role role = roleService.findById(roleId);
            hero.setRole(role);
        });

        return heroRepository.save(hero);
    }
}
