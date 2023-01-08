package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.payload.WeaponDetails;
import com.maigrand.overwatchdb.repository.WeaponRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponService {

    private final WeaponRepository weaponRepository;
    private final AimTypeService aimTypeService;
    private final HeroService heroService;

    public WeaponService(WeaponRepository weaponRepository, AimTypeService aimTypeService, HeroService heroService) {
        this.weaponRepository = weaponRepository;
        this.aimTypeService = aimTypeService;
        this.heroService = heroService;
    }

    public List<Weapon> findAll() {
        return weaponRepository.findAll();
    }

    public Weapon findById(Long id) {
        return weaponRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Weapon create(WeaponDetails details) {
        Weapon weapon = new Weapon();
        weapon.setName(details.getName());
        weapon.setAimType(aimTypeService.findById(details.getAimTypeId()));
        weapon.setHero(heroService.findById(details.getHeroId()));

        return weaponRepository.save(weapon);
    }
}
