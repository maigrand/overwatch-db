package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.RoleDetails;
import com.maigrand.overwatchdb.repository.RoleRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<Role> findAll(Specification<Role> specification, Pageable pageable) {
        return roleRepository.findAll(specification, pageable);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Validated(OnCreate.class)
    public Role create(@Valid RoleDetails details) {
        Role role = new Role();
        role.setName(details.getName());
        role.setGroupBuff(details.getGroupBuff());

        return roleRepository.save(role);
    }

    @Validated(OnUpdate.class)
    public Role update(Integer id, @Valid RoleDetails details) {
        Role role = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(role::setName);
        Optional.ofNullable(details.getGroupBuff()).ifPresent(role::setGroupBuff);

        return roleRepository.save(role);
    }
}
