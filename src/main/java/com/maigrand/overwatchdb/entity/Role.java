package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="role")
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Column(name = "group_buff", nullable = false)
    private String groupBuff;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<Hero> heroes;
}
