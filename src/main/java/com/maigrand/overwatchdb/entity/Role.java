package com.maigrand.overwatchdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "group_buff", nullable = false)
    private String groupBuff;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<Hero> heroes;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(groupBuff, role.groupBuff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupBuff);
    }
}
