package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.searchcriteria.HeroSearchCriteria;
import com.maigrand.overwatchdb.service.HeroService;
import com.maigrand.overwatchdb.specification.HeroSpecificationBuilder;
import com.maigrand.overwatchdb.view.HeroView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/hero")
@Tag(name = "USER: Hero")
public class HeroController {

    private final HeroService heroService;

    private final HeroSpecificationBuilder specificationBuilder;

    public HeroController(HeroService heroService, HeroSpecificationBuilder specificationBuilder) {
        this.heroService = heroService;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public ResponseEntity<List<HeroView>> findAll(@Valid HeroSearchCriteria searchCriteria, Pageable pageable) {
        Specification<Hero> specification = specificationBuilder.build(searchCriteria);
        Page<Hero> heroes = heroService.findAll(specification, pageable);
        List<HeroView> heroViews = heroes.stream().map(HeroView::new).collect(Collectors.toList());
        return ResponseEntity.ok(heroViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroView> findById(@PathVariable("id") Integer id) {
        Hero hero = heroService.findById(id);
        return ResponseEntity.ok(new HeroView(hero));
    }
}
