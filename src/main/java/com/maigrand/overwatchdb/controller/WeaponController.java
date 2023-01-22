package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.searchcriteria.WeaponSearchCriteria;
import com.maigrand.overwatchdb.service.WeaponService;
import com.maigrand.overwatchdb.specification.WeaponSpecificationBuilder;
import com.maigrand.overwatchdb.view.WeaponView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/weapon")
@Tag(name = "USER: Weapon")
public class WeaponController {

    private final WeaponService weaponService;

    private final WeaponSpecificationBuilder specificationBuilder;

    public WeaponController(WeaponService weaponService, WeaponSpecificationBuilder specificationBuilder) {
        this.weaponService = weaponService;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public ResponseEntity<List<WeaponView>> findAll(@Valid WeaponSearchCriteria searchCriteria, Pageable pageable) {
        Specification<Weapon> weaponSpecification = specificationBuilder.build(searchCriteria);
        Page<Weapon> weapons = weaponService.findAll(weaponSpecification, pageable);
        List<WeaponView> weaponViews = weapons.stream().map(WeaponView::new).collect(Collectors.toList());
        return ResponseEntity.ok(weaponViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponView> findById(@PathVariable("id") Integer id) {
        Weapon weapon = weaponService.findById(id);
        return ResponseEntity.ok(new WeaponView(weapon));
    }
}
