package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.AbstractTest;
import com.maigrand.overwatchdb.entity.Weapon;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponServiceTest extends AbstractTest {

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AimTypeService aimTypeService;

    @Autowired
    private HeroService heroService;

    @Test
    @Commit
    public void create() {
        WeaponDetails details = new WeaponDetails();
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

        Weapon weapon = weaponService.create(details);

        assertEquals(weapon.getName(), "TEST_NAME");
        assertEquals(weapon.getAimType().getId(), 1);
        assertEquals(weapon.getHero().getId(), 1);
    }

    @Test
    public void findById() {
        Weapon weapon = weaponService.findById(1);

        assertEquals(weapon.getName(), "TEST_NAME");
        assertEquals(weapon.getAimType().getId(), 1);
        assertEquals(weapon.getHero().getId(), 1);
    }

    @Test
    public void findById_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            heroService.findById(999999);
        });
    }

    @Test
    public void findAll() {
        List<Weapon> weapons = weaponService.findAll();

        assertEquals(1, weapons.size());
        assertEquals(weapons.get(0).getName(), "TEST_NAME");
        assertEquals(weapons.get(0).getAimType().getId(), 1);
        assertEquals(weapons.get(0).getHero().getId(), 1);
    }
}
