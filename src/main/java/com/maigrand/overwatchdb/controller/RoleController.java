package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.service.RoleService;
import com.maigrand.overwatchdb.view.RoleView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "USER: Role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleView>> findAll() {
        List<Role> roles = roleService.findAll();
        List<RoleView> roleViews = roles.stream().map(RoleView::new).collect(Collectors.toList());
        return ResponseEntity.ok(roleViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleView> findById(@PathVariable("id") Integer id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(new RoleView(role));
    }
}
