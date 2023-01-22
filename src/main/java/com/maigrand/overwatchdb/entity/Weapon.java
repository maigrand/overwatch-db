package com.maigrand.overwatchdb.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "damage_min", nullable = false)
    private Float damageMin;

    @Column(name = "damage_max", nullable = false)
    private Float damageMax;

    @Column(name = "falloff_range_min", nullable = false)
    private Integer falloffRangeMin;

    @Column(name = "falloff_range_max", nullable = false)
    private Integer falloffRangeMax;

    @Column(name = "spread_angle")
    private String spreadAngle;

    @Column(name = "rate_of_fire", nullable = false)
    private String rateOfFire;

    @Column(name = "ammo", nullable = false)
    private Integer ammo;

    @Column(name = "reload_time", nullable = false)
    private Float reloadTime;

    @Column(name = "headshot", nullable = false)
    private boolean headshot;

    @Column(name = "description")
    private String description;

    @Column(name = "blocked_by_barriers")
    private boolean blockedByBarriers;

    //@TODO: привязать к разным ability
    //Many to Many
    //Set<Ability> blockedBy;
    //Set<Ability> boostedBy;
//
//    @Column(name = "blocked_by_defence_matrix")
//    private boolean blockedByDefenceMatrix;
//
//    @Column(name = "blocked_by_kinetic_grasp")
//    private boolean blockedByKineticGrasp;
//
//    @Column(name="blocked_by_deflect")
//    private boolean blockedByDeflect;
//
//    @Column(name = "blocked_by_javelin_spin")
//    private boolean blockedByJavelinSpin;
//
//    @Column(name="affected_by_damage_boost")
//    private boolean affectedByDamageBoost;
//
//    @Column(name = "affected_by_amplification_matrix")
//    private boolean affectedByAmplificationMatrix;

    //@TODO: List<String> details

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
