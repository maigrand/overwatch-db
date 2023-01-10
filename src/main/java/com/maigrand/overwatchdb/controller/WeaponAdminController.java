package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.payload.WeaponDetails;
import com.maigrand.overwatchdb.service.WeaponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/weapon")
@Tag(name = "ADMIN: Weapon")
public class WeaponAdminController {

    private final WeaponService weaponService;

    public WeaponAdminController(WeaponService weaponService) {
        this.weaponService = weaponService;
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
