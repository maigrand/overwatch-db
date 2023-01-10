package com.maigrand.overwatchdb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "aimtype")
@Getter
@Setter
@NoArgsConstructor
public class AimType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "aimType", fetch = FetchType.EAGER)
    private Set<Weapon> weapons;

    @OneToMany(mappedBy = "aimType", fetch = FetchType.EAGER)
    private Set<Ability> abilities;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AimType aimType = (AimType) o;
        return Objects.equals(id, aimType.id) && Objects.equals(type, aimType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
