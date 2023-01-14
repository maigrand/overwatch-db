package com.maigrand.overwatchdb.controller.admin;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.payload.AbilityDetails;
import com.maigrand.overwatchdb.service.AbilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ability")
@Tag(name = "ADMIN: Ability")
public class AbilityAdminController {
    private final AbilityService abilityService;

    public AbilityAdminController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @PostMapping
    public ResponseEntity<Ability> create(@RequestBody AbilityDetails details) {
        Ability ability = abilityService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(ability);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ability> update(@PathVariable("id") Integer id, @RequestBody AbilityDetails details) {
        Ability ability = abilityService.update(id, details);
        return ResponseEntity.ok(ability);
    }
}
