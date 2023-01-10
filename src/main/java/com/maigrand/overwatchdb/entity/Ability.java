package com.maigrand.overwatchdb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ability")
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ability ability = (Ability) o;
        return Objects.equals(id, ability.id) && Objects.equals(name, ability.name)
                && Objects.equals(aimType, ability.aimType)
                && Objects.equals(hero, ability.hero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, aimType, hero);
    }
}
