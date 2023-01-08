package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ability")
@Getter
@Setter
public class Ability {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "aimtype_id",
            foreignKey = @ForeignKey(name = "fk_ability_aimtype")
    )
    private AimType aimType;

    @ManyToOne
    @JoinColumn(
            name = "hero_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ability_hero")
    )
    private Hero hero;
}
