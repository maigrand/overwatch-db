package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.searchcriteria.AimTypeSearchCriteria;
import com.maigrand.overwatchdb.service.AimTypeService;
import com.maigrand.overwatchdb.specification.AimTypeSpecificationBuilder;
import com.maigrand.overwatchdb.view.AimTypeView;
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
@RequestMapping("/api/v1/aimtype")
@Tag(name = "USER: AimType")
public class AimTypeController {

    private final AimTypeService aimTypeService;

    private final AimTypeSpecificationBuilder specificationBuilder;

    public AimTypeController(AimTypeService aimTypeService, AimTypeSpecificationBuilder specificationBuilder) {
        this.aimTypeService = aimTypeService;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public ResponseEntity<List<AimTypeView>> findAll(@Valid AimTypeSearchCriteria searchCriteria, Pageable pageable) {
        Specification<AimType> aimTypeSpecification = specificationBuilder.build(searchCriteria);
        Page<AimType> aimTypes = aimTypeService.findAll(aimTypeSpecification, pageable);
        List<AimTypeView> aimTypeViews = aimTypes.stream().map(AimTypeView::new).collect(Collectors.toList());
        return ResponseEntity.ok(aimTypeViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimTypeView> findById(@PathVariable("id") Integer id) {
        AimType aimType = aimTypeService.findById(id);
        return ResponseEntity.ok(new AimTypeView(aimType));
    }
}
