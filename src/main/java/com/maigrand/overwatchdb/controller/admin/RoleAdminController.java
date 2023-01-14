package com.maigrand.overwatchdb.controller.admin;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.payload.RoleDetails;
import com.maigrand.overwatchdb.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/role")
@Tag(name = "ADMIN: Role")
public class RoleAdminController {

    private final RoleService roleService;

    public RoleAdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody RoleDetails details) {
        Role role = roleService.create(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable("id") Integer id, @RequestBody RoleDetails details) {
        Role role = roleService.update(id, details);
        return ResponseEntity.ok(role);
    }
}