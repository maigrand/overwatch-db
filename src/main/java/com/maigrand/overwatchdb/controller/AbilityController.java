package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.payload.AbilityDetails;
import com.maigrand.overwatchdb.service.AbilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/ability")
public class AbilityController {
    private final AbilityService abilityService;

    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @GetMapping
    public ResponseEntity<List<Ability>> findAll() {
        List<Ability> abilities = abilityService.findAll();
        return ResponseEntity.ok(abilities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ability> findById(@PathVariable("id") Long id) {
        Ability ability = abilityService.findById(id);
        return ResponseEntity.ok(ability);
    }

    @PostMapping
    public ResponseEntity<Ability> create(@RequestBody AbilityDetails details) {
        Ability ability = abilityService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(ability);
    }
}
