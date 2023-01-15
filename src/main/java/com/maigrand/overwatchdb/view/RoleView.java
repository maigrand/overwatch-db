package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Role;
import lombok.Getter;

@Getter
public class RoleView {

    private final Integer id;

    private final String name;

    private final String groupBuff;

    public RoleView(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.groupBuff = role.getGroupBuff();
    }
}
