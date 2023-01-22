package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.searchcriteria.AbilitySearchCriteria;
import com.maigrand.overwatchdb.service.AbilityService;
import com.maigrand.overwatchdb.specification.AbilitySpecificationBuilder;
import com.maigrand.overwatchdb.view.AbilityView;
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
@RequestMapping("/api/v1/ability")
@Tag(name = "USER: Ability")
public class AbilityController {

    private final AbilityService abilityService;

    private final AbilitySpecificationBuilder specificationBuilder;

    public AbilityController(AbilityService abilityService, AbilitySpecificationBuilder specificationBuilder) {
        this.abilityService = abilityService;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public ResponseEntity<List<AbilityView>> findAll(@Valid AbilitySearchCriteria searchCriteria, Pageable pageable) {
        Specification<Ability> abilitySpecification = specificationBuilder.build(searchCriteria);
        Page<Ability> abilities = abilityService.findAll(abilitySpecification, pageable);
        List<AbilityView> abilityViews = abilities.stream().map(AbilityView::new).collect(Collectors.toList());
        return ResponseEntity.ok(abilityViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbilityView> findById(@PathVariable("id") Integer id) {
        Ability ability = abilityService.findById(id);
        return ResponseEntity.ok(new AbilityView(ability));
    }
}
