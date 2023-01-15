package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.service.AimTypeService;
import com.maigrand.overwatchdb.view.AimTypeView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/aimtype")
@Tag(name = "USER: AimType")
public class AimTypeController {

    private final AimTypeService aimTypeService;

    public AimTypeController(AimTypeService aimTypeService) {
        this.aimTypeService = aimTypeService;
    }

    @GetMapping
    public ResponseEntity<List<AimTypeView>> findAll() {
        List<AimType> aimTypes = aimTypeService.findAll();
        List<AimTypeView> aimTypeViews = aimTypes.stream().map(AimTypeView::new).collect(Collectors.toList());
        return ResponseEntity.ok(aimTypeViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimTypeView> findById(@PathVariable("id") Integer id) {
        AimType aimType = aimTypeService.findById(id);
        return ResponseEntity.ok(new AimTypeView(aimType));
    }
}
