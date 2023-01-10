package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "weapon")
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weapon weapon = (Weapon) o;
        return Objects.equals(id, weapon.id) && Objects.equals(name, weapon.name)
                && Objects.equals(aimType, weapon.aimType)
                && Objects.equals(hero, weapon.hero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, aimType, hero);
    }
}
