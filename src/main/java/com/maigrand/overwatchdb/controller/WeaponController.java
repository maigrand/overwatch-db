package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.service.WeaponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weapon")
@Tag(name = "USER: Weapon")
public class WeaponController {

    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping
    public ResponseEntity<List<Weapon>> findAll() {
        List<Weapon> weapons = weaponService.findAll();
        return ResponseEntity.ok(weapons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weapon> findById(@PathVariable("id") Integer id) {
        Weapon weapon = weaponService.findById(id);
        return ResponseEntity.ok(weapon);
    }
}
