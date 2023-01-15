package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.service.AbilityService;
import com.maigrand.overwatchdb.view.AbilityView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ability")
@Tag(name = "USER: Ability")
public class AbilityController {

    private final AbilityService abilityService;

    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @GetMapping
    public ResponseEntity<List<AbilityView>> findAll() {
        List<Ability> abilities = abilityService.findAll();
        List<AbilityView> abilityViews = abilities.stream().map(AbilityView::new).collect(Collectors.toList());
        return ResponseEntity.ok(abilityViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbilityView> findById(@PathVariable("id") Integer id) {
        Ability ability = abilityService.findById(id);
        return ResponseEntity.ok(new AbilityView(ability));
    }
}
