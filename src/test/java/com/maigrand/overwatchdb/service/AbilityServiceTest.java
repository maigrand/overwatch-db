package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.AbstractTest;
import com.maigrand.overwatchdb.entity.Ability;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbilityServiceTest extends AbstractTest {

    @Autowired
    private AbilityService abilityService;

    @Autowired
    private AimTypeService aimTypeService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private RoleService roleService;

    @Test
    @Commit
    public void create() {
        AbilityDetails details = new AbilityDetails();
        RoleDetails roleDetails = new RoleDetails();
        AimTypeDetails aimTypeDetails = new AimTypeDetails();
        HeroDetails heroDetails = new HeroDetails();

        roleDetails.setName("TEST_NAME");
        roleDetails.setGroupBuff("TEST_GROUP_BUFF");
        roleService.create(roleDetails);

        aimTypeDetails.setType("TEST_TYPE");
        aimTypeService.create(aimTypeDetails);

        heroDetails.setName("TEST_NAME");
        heroDetails.setRoleId(1);
        heroService.create(heroDetails);

        details.setName("TEST_NAME");
        details.setAimTypeId(1);
        details.setHeroId(1);

        Ability ability = abilityService.create(details);

        assertEquals(ability.getName(), "TEST_NAME");
        assertEquals(ability.getAimType().getId(), 1);
        assertEquals(ability.getHero().getId(), 1);
    }

    @Test
    public void findById() {
        Ability ability = abilityService.findById(1);

        assertEquals(ability.getName(), "TEST_NAME");
        assertEquals(ability.getAimType().getId(), 1);
        assertEquals(ability.getHero().getId(), 1);
    }

    @Test
    public void findById_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            abilityService.findById(999999);
        });
    }

    @Test
    public void findAll() {
        List<Ability> abilities = abilityService.findAll();

        assertEquals(1, abilities.size());
        assertEquals(abilities.get(0).getName(), "TEST_NAME");
        assertEquals(abilities.get(0).getAimType().getId(), 1);
        assertEquals(abilities.get(0).getHero().getId(), 1);
    }
}
