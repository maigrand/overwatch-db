package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.payload.WeaponDetails;
import com.maigrand.overwatchdb.repository.WeaponRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
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

    public Weapon findById(Integer id) {
        return weaponRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Validated(OnCreate.class)
    public Weapon create(@Valid WeaponDetails details) {
        Weapon weapon = new Weapon();
        weapon.setName(details.getName());
        weapon.setAimType(aimTypeService.findById(details.getAimTypeId()));
        weapon.setHero(heroService.findById(details.getHeroId()));

        return weaponRepository.save(weapon);
    }

    @Validated(OnUpdate.class)
    public Weapon update(Integer id, @Valid WeaponDetails details) {
        Weapon weapon = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(weapon::setName);
        Optional.ofNullable(details.getAimTypeId()).ifPresent((aimTypeId) -> {
            AimType aimType = aimTypeService.findById(aimTypeId);
            weapon.setAimType(aimType);
        });
        Optional.ofNullable(details.getHeroId()).ifPresent((heroId) -> {
            Hero hero = heroService.findById(heroId);
            weapon.setHero(hero);
        });

        return weaponRepository.save(weapon);
    }
}
