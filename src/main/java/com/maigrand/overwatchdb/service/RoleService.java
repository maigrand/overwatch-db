package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.payload.AbilityDetails;
import com.maigrand.overwatchdb.payload.RoleDetails;
import com.maigrand.overwatchdb.repository.RoleRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Validated(OnCreate.class)
    public Role create(RoleDetails details) {
        Role role = new Role();
        role.setName(details.getName());
        role.setGroupBuff(details.getGroupBuff());

        return roleRepository.save(role);
    }

    @Validated(OnUpdate.class)
    public Role update(Integer id, RoleDetails details) {
        Role role = findById(id);
        Optional.ofNullable(details.getName()).ifPresent(role::setName);
        Optional.ofNullable(details.getGroupBuff()).ifPresent(role::setGroupBuff);

        return roleRepository.save(role);
    }
}
