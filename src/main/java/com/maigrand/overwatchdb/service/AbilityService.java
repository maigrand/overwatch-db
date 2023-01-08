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

    public AbilityService(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
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
        ability.setAimType(details.getAimType());
        ability.setHero(details.getHero());

        return abilityRepository.save(ability);
    }
}
