package com.maigrand.overwatchdb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "hero")
@Getter
@Setter
@NoArgsConstructor
public class Hero {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(name = "fk_hero_role")
    )
    private Role role;

    @OneToMany(mappedBy = "hero", fetch = FetchType.EAGER)
    private Set<Weapon> weapons;

    @OneToMany(mappedBy = "hero", fetch = FetchType.EAGER)
    private Set<Ability> abilities;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id) && Objects.equals(name, hero.name) && Objects.equals(role, hero.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }
}
