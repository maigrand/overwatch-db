package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.payload.AimTypeDetails;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AimTypeServiceTest {

    @Autowired
    private AimTypeService aimTypeService;

    @Test
    @Commit
    public void create() {
        AimTypeDetails details = new AimTypeDetails();
        details.setType("TEST_TYPE");

        AimType aimType = aimTypeService.create(details);

        assertEquals(aimType.getType(), "TEST_TYPE");
    }

    @Test
    public void findById() {
        AimType aimType = aimTypeService.findById(1L);

        assertEquals(aimType.getType(), "TEST_TYPE");
    }

    @Test
    public void findById_throw_not_found() {
        assertThrows(EntityNotFoundException.class, () -> {
            aimTypeService.findById(999999L);
        });
    }

    @Test
    public void findAll() {
        List<AimType> aimTypes = aimTypeService.findAll();

        assertEquals(1, aimTypes.size());
        assertEquals(aimTypes.get(0).getType(), "TEST_TYPE");
    }
}
