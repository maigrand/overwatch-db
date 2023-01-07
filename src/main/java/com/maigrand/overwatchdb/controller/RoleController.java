package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.payload.RoleDetails;
import com.maigrand.overwatchdb.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") Long id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody RoleDetails roleDetails) {
        Role role = roleService.create(roleDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
}
