package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.AimType;
import lombok.Getter;

@Getter
public class AimTypeView {

    private final Integer id;

    private final String type;

    public AimTypeView(AimType aimType) {
        this.id = aimType.getId();
        this.type = aimType.getType();
    }
}
