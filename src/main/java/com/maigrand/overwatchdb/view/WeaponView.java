package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Weapon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class WeaponView {

    @Schema(title = "weapon id")
    private final Integer id;

    @Schema(title = "Weapon name")
    private final String name;

    @Schema(title = "fire type", description = "Primary fire or Secondary fire")
    private final String usage;

    @Schema(title = "min damage", description = "minimum damage with maximum falloff range")
    private final Float damageMin;

    @Schema(title = "max damage", description = "maximum damage with minimum falloff range")
    private final Float damageMax;

    @Schema(title = "min falloff range", description = "Distances at which damage falloff starts")
    private final Integer falloffRangeMin;

    @Schema(title = "max falloff range", description = "Distances at which damage falloff ends")
    private final Integer falloffRangeMax;

    @Schema(title = "spread angle",
            description = "Inaccuracy of the weapon, or angle of area-of-effect abilities (if applicable)")
    private final String spreadAngle;

    @Schema(title = "rate of fire")
    private final String rateOfFire;

    @Schema(title = "ammo")
    private final Integer ammo;

    @Schema(title = "reload time", description = "{value} seconds animation")
    private final Float reloadTime;

    @Schema(title = "headshot", description = "critical damage multiplier is available")
    private final boolean headshot;

    @Schema(title = "description")
    private final String description;

    @Schema(title = "aim type", description = "projectile or hitscan")
    private final AimTypeView aimType;

    public WeaponView(Weapon weapon) {
        this.id = weapon.getId();
        this.name = weapon.getName();
        this.usage = weapon.getUsage();
        this.damageMin = weapon.getDamageMin();
        this.damageMax = weapon.getDamageMax();
        this.falloffRangeMin = weapon.getFalloffRangeMin();
        this.falloffRangeMax = weapon.getFalloffRangeMax();
        this.spreadAngle = weapon.getSpreadAngle();
        this.rateOfFire = weapon.getRateOfFire();
        this.ammo = weapon.getAmmo();
        this.reloadTime = weapon.getReloadTime();
        this.headshot = weapon.isHeadshot();
        this.description = weapon.getDescription();
        this.aimType = new AimTypeView(weapon.getAimType());
    }
}
