package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.payload.WeaponDetails;
import com.maigrand.overwatchdb.service.WeaponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/weapon")
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

    @PostMapping
    public ResponseEntity<Weapon> create(@RequestBody WeaponDetails details) {
        Weapon weapon = weaponService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(weapon);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Weapon> update(@PathVariable("id") Integer id, @RequestBody WeaponDetails details) {
        Weapon weapon = weaponService.update(id, details);
        return ResponseEntity.ok(weapon);
    }
}
