package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weapon")
@Getter
@Setter
public class Weapon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "aimtype_id",
            foreignKey = @ForeignKey(name = "fk_weapon_aimtype")
    )
    private AimType aimType;

    @ManyToOne
    @JoinColumn(
            name = "hero_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_weapon_hero")
    )
    private Hero hero;
}
