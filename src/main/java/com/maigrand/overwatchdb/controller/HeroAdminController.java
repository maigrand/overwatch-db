package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.payload.HeroDetails;
import com.maigrand.overwatchdb.service.HeroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/hero")
@Tag(name = "ADMIN: Hero")
public class HeroAdminController {

    private final HeroService heroService;

    public HeroAdminController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody HeroDetails details) {
        Hero hero = heroService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(hero);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Hero> update(@PathVariable("id") Integer id, @RequestBody HeroDetails details) {
        Hero hero = heroService.update(id, details);
        return ResponseEntity.ok(hero);
    }
}
