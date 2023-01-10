package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AbilityDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{aimTypeId.not_null}", groups = OnCreate.class)
    private Integer aimTypeId;

    @NotNull(message = "{heroId.not_null}", groups = OnCreate.class)
    private Integer heroId;
}
