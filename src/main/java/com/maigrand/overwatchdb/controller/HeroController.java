package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.payload.HeroDetails;
import com.maigrand.overwatchdb.service.HeroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/hero")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<List<Hero>> findAll() {
        List<Hero> heroes = heroService.findAll();
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> findById(@PathVariable("id") Integer id) {
        Hero hero = heroService.findById(id);
        return ResponseEntity.ok(hero);
    }

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody HeroDetails details) {
        Hero hero = heroService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(hero);
    }
}
