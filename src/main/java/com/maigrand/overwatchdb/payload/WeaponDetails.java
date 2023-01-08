package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.entity.Hero;
import lombok.Data;

@Data
public class WeaponDetails {

    private String name;
    private AimType aimType;
    private Hero hero;
}
