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

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Hero findById(Long id) {
        return heroRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Hero create(HeroDetails details) {
        Hero hero = new Hero();
        hero.setName(details.getName());

        return heroRepository.save(hero);
    }
}
