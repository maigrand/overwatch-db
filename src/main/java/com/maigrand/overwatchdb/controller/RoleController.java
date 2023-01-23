package com.maigrand.overwatchdb.controller;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.searchcriteria.RoleSearchCriteria;
import com.maigrand.overwatchdb.service.RoleService;
import com.maigrand.overwatchdb.specification.RoleSpecificationBuilder;
import com.maigrand.overwatchdb.view.RoleView;
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
@RequestMapping("/api/v1/role")
@Tag(name = "USER: Role")
public class RoleController {

    private final RoleService roleService;

    private final RoleSpecificationBuilder specificationBuilder;

    public RoleController(RoleService roleService, RoleSpecificationBuilder specificationBuilder) {
        this.roleService = roleService;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public ResponseEntity<List<RoleView>> findAll(@Valid RoleSearchCriteria roleSearchCriteria, Pageable pageable) {
        Specification<Role> specification = specificationBuilder.build(roleSearchCriteria);
        Page<Role> roles = roleService.findAll(specification, pageable);
        List<RoleView> roleViews = roles.stream().map(RoleView::new).collect(Collectors.toList());
        return ResponseEntity.ok(roleViews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleView> findById(@PathVariable("id") Integer id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(new RoleView(role));
    }
}
