package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.service.HeroService;
import com.maigrand.overwatchdb.view.HeroView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hero")
@Tag(name = "USER: Hero")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public ResponseEntity<List<HeroView>> findAll() {
        List<Hero> heroes = heroService.findAll();
        List<HeroView> heroViews = heroes.stream().map(HeroView::new).collect(Collectors.toList());
        return ResponseEntity.ok(heroViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroView> findById(@PathVariable("id") Integer id) {
        Hero hero = heroService.findById(id);
        return ResponseEntity.ok(new HeroView(hero));
    }
}
