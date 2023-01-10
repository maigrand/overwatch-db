package com.maigrand.overwatchdb.controller;


import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.service.AimTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aimtype")
@Tag(name = "USER: AimType")
public class AimTypeController {

    private final AimTypeService aimTypeService;

    public AimTypeController(AimTypeService aimTypeService) {
        this.aimTypeService = aimTypeService;
    }

    @GetMapping
    public ResponseEntity<List<AimType>> findAll() {
        List<AimType> aimTypes = aimTypeService.findAll();
        return ResponseEntity.ok(aimTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimType> findById(@PathVariable("id") Integer id) {
        AimType aimType = aimTypeService.findById(id);
        return ResponseEntity.ok(aimType);
    }
}
