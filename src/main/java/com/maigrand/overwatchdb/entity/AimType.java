package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="aimtype")
@Getter
@Setter
public class AimType {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "aimType", fetch = FetchType.EAGER)
    private Set<Weapon> weapons;

    @OneToMany(mappedBy = "aimType", fetch = FetchType.EAGER)
    private Set<Ability> abilities;
}
