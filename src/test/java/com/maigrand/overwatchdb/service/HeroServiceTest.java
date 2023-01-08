package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.AbstractTest;
import com.maigrand.overwatchdb.entity.Hero;
import com.maigrand.overwatchdb.payload.HeroDetails;
import com.maigrand.overwatchdb.payload.RoleDetails;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeroServiceTest extends AbstractTest {
    @Autowired
    private HeroService heroService;

    @Autowired
    private RoleService roleService;

    @Test
    @Commit
    public void create() {
        HeroDetails details = new HeroDetails();
        RoleDetails roleDetails = new RoleDetails();

        roleDetails.setName("TEST_NAME");
        roleDetails.setGroupBuff("TEST_GROUP_BUFF");
        roleService.create(roleDetails);

        details.setName("TEST_NAME");
        details.setRoleId(1);

        Hero hero = heroService.create(details);

        assertEquals(hero.getName(), "TEST_NAME");
        assertEquals(hero.getRole().getId(), 1);
    }

    @Test
    public void findById() {
        Hero hero = heroService.findById(1);

        assertEquals(hero.getName(), "TEST_NAME");
        assertEquals(hero.getRole().getId(), 1);
    }

    @Test
    public void findById_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            heroService.findById(999999);
        });
    }

    @Test
    public void findAll() {
        List<Hero> heroes = heroService.findAll();

        assertEquals(1, heroes.size());
        assertEquals(heroes.get(0).getName(), "TEST_NAME");
        assertEquals(heroes.get(0).getRole().getId(), 1);
    }
}
