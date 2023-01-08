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

    public WeaponService(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
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

        return weaponRepository.save(weapon);
    }
}
