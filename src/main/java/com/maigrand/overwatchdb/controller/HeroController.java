package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.service.HeroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hero")
@Tag(name = "USER: Hero")
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
}
