package com.maigrand.overwatchdb.controller.admin;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.payload.AimTypeDetails;
import com.maigrand.overwatchdb.service.AimTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/aimtype")
@Tag(name = "ADMIN: AimType")
public class AimTypeAdminController {

    private final AimTypeService aimTypeService;

    public AimTypeAdminController(AimTypeService aimTypeService) {
        this.aimTypeService = aimTypeService;
    }

    @PostMapping
    public ResponseEntity<AimType> create(@RequestBody AimTypeDetails details) {
        AimType aimType = aimTypeService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(aimType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimType> update(@PathVariable("id") Integer id, @RequestBody AimTypeDetails details) {
        AimType aimType = aimTypeService.update(id, details);
        return ResponseEntity.ok(aimType);
    }
}
