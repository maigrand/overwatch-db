package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HeroDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{roleId.not_null}", groups = OnCreate.class)
    private Integer roleId;
}
