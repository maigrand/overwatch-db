package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Weapon;
import lombok.Getter;

@Getter
public class WeaponView {

    private final Integer id;

    private final String name;

    private final AimTypeView aimType;

    public WeaponView(Weapon weapon) {
        this.id = weapon.getId();
        this.name = weapon.getName();
        this.aimType = new AimTypeView(weapon.getAimType());
    }
}
