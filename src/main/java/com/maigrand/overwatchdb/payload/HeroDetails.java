package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HeroDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{roleId.not_null}", groups = OnCreate.class)
    private Integer roleId;
}
