package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.payload.AimTypeDetails;
import com.maigrand.overwatchdb.service.AimTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/aimtype")
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

    @PostMapping
    public ResponseEntity<AimType> create(@RequestBody AimTypeDetails details) {
        AimType aimType = aimTypeService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(aimType);
    }
}
