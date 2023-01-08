package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.AbstractTest;
import com.maigrand.overwatchdb.entity.Role;
import com.maigrand.overwatchdb.payload.RoleDetails;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleServiceTest extends AbstractTest {
    @Autowired
    private RoleService roleService;

    @Test
    @Commit
    public void create() {
        RoleDetails details = new RoleDetails();
        details.setName("TEST_NAME");
        details.setGroupBuff("TEST_GROUP_BUFF");

        Role role = roleService.create(details);

        assertEquals(role.getName(), "TEST_NAME");
        assertEquals(role.getGroupBuff(), "TEST_GROUP_BUFF");
    }

    @Test
    public void findById() {
        Role role = roleService.findById(1);

        assertEquals(role.getName(), "TEST_NAME");
        assertEquals(role.getGroupBuff(), "TEST_GROUP_BUFF");
    }

    @Test
    public void findById_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            roleService.findById(999999);
        });
    }

    @Test
    public void findAll() {
        List<Role> roles = roleService.findAll();

        assertEquals(1, roles.size());
        assertEquals(roles.get(0).getName(), "TEST_NAME");
        assertEquals(roles.get(0).getGroupBuff(), "TEST_GROUP_BUFF");
    }
}
