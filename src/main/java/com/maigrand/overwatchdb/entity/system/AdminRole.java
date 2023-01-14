package com.maigrand.overwatchdb.entity.system;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="admin_role")
@Getter
@Setter
@NoArgsConstructor
public class AdminRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "adminRole", fetch = FetchType.EAGER)
    private Set<AdminUser> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdminRole adminRole = (AdminRole) o;
        return Objects.equals(id, adminRole.id) && Objects.equals(name, adminRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
