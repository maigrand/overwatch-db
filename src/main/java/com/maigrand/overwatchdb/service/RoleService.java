package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.payload.RoleDetails;
import com.maigrand.overwatchdb.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Role create(RoleDetails details) {
        Role role = new Role();
        role.setName(details.getName());
        role.setGroupBuff(details.getGroupBuff());

        return roleRepository.save(role);
    }
}
