package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.*;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.AbilityDetails;
import com.maigrand.overwatchdb.repository.AbilityRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AbilityService {

    private final AbilityRepository abilityRepository;
    private final AimTypeService aimTypeService;
    private final HeroService heroService;

    public AbilityService(AbilityRepository abilityRepository, AimTypeService aimTypeService, HeroService heroService) {
        this.abilityRepository = abilityRepository;
        this.aimTypeService = aimTypeService;
        this.heroService = heroService;
    }

    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    public Ability findById(Integer id) {
        return abilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ability not found"));
    }

    @Validated(OnCreate.class)
    public Ability create(AbilityDetails details) {
        Ability ability = new Ability();
        ability.setName(details.getName());
        ability.setAimType(aimTypeService.findById(details.getAimTypeId()));
        ability.setHero(heroService.findById(details.getHeroId()));

        return abilityRepository.save(ability);
    }

    @Validated(OnUpdate.class)
    public Ability update(Integer id, AbilityDetails details) {
        Ability ability = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(ability::setName);
        Optional.ofNullable(details.getAimTypeId()).ifPresent((aimTypeId) -> {
            AimType aimType = aimTypeService.findById(aimTypeId);
            ability.setAimType(aimType);
        });
        Optional.ofNullable(details.getHeroId()).ifPresent((heroId) -> {
            Hero hero = heroService.findById(heroId);
            ability.setHero(hero);
        });

        return abilityRepository.save(ability);
    }
}
