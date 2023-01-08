package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.payload.AbilityDetails;
import com.maigrand.overwatchdb.repository.AbilityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Ability findById(Long id) {
        return abilityRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Ability create(AbilityDetails details) {
        Ability ability = new Ability();
        ability.setName(details.getName());
        ability.setAimType(aimTypeService.findById(details.getAimTypeId()));
        ability.setHero(heroService.findById(details.getAimTypeId()));

        return abilityRepository.save(ability);
    }
}
