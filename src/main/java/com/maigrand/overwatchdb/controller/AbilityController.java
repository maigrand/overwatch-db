package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.service.AbilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ability")
@Tag(name = "USER: Ability")
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
    public ResponseEntity<Ability> findById(@PathVariable("id") Integer id) {
        Ability ability = abilityService.findById(id);
        return ResponseEntity.ok(ability);
    }
}
