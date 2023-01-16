package com.maigrand.overwatchdb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "weapon")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Weapon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "usage", nullable = false)
    private String usage;

    @Column(name = "damage", nullable = false)
    private String damage;

    @Column(name = "falloff_range", nullable = false)
    private String falloffRange;

    @Column(name = "spread_angle", nullable = false)
    private String spreadAngle;

    @Column(name = "rate_of_fire", nullable = false)
    private String rateOfFire;

    @Column(name = "ammo", nullable = false)
    private Integer ammo;

    @Column(name = "reload_time", nullable = false)
    private Float reloadTime;

    @Column(name = "headshot", nullable = false)
    private boolean headshot;

    @ManyToOne
    @JoinColumn(
            name = "aimtype_id",
            foreignKey = @ForeignKey(name = "fk_weapon_aimtype")
    )
    @EqualsAndHashCode.Exclude
    private AimType aimType;

    @ManyToOne
    @JoinColumn(
            name = "hero_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_weapon_hero")
    )
    @EqualsAndHashCode.Exclude
    private Hero hero;
}
