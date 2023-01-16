package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Weapon;
import lombok.Getter;

@Getter
public class WeaponView {

    private final Integer id;

    private final String name;

    private final String usage;

    private final String damage;

    private final String falloffRange;

    private final String spreadAngle;

    private final String rateOfFire;

    private final Integer ammo;

    private final Float reloadTime;

    private final boolean headshot;

    private final AimTypeView aimType;

    public WeaponView(Weapon weapon) {
        this.id = weapon.getId();
        this.name = weapon.getName();
        this.usage = weapon.getUsage();
        this.damage = weapon.getDamage();
        this.falloffRange = weapon.getFalloffRange();
        this.spreadAngle = weapon.getSpreadAngle();
        this.rateOfFire = weapon.getRateOfFire();
        this.ammo = weapon.getAmmo();
        this.reloadTime = weapon.getReloadTime();
        this.headshot = weapon.isHeadshot();
        this.aimType = new AimTypeView(weapon.getAimType());
    }
}
