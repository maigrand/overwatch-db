package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.service.WeaponService;
import com.maigrand.overwatchdb.view.WeaponView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/weapon")
@Tag(name = "USER: Weapon")
public class WeaponController {

    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping
    public ResponseEntity<List<WeaponView>> findAll() {
        List<Weapon> weapons = weaponService.findAll();
        List<WeaponView> weaponViews = weapons.stream().map(WeaponView::new).collect(Collectors.toList());
        return ResponseEntity.ok(weaponViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponView> findById(@PathVariable("id") Integer id) {
        Weapon weapon = weaponService.findById(id);
        return ResponseEntity.ok(new WeaponView(weapon));
    }
}
