package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "hero")
@Getter
@Setter
public class Hero {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
