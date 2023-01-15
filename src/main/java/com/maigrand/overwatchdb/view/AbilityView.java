package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Ability;
import lombok.Getter;

import java.util.Optional;

@Getter
public class AbilityView {

    private final Integer id;

    private final String name;

    private AimTypeView aimType;

    public AbilityView(Ability ability) {
        this.id = ability.getId();
        this.name = ability.getName();
        Optional.ofNullable(ability.getAimType()).ifPresent((aimType1) -> this.aimType = new AimTypeView(aimType1));
        //this.aimType = new AimTypeView(ability.getAimType());
    }
}
