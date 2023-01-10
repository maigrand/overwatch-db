package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AbilityDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{aimTypeId.not_null}", groups = OnCreate.class)
    private Integer aimTypeId;

    @NotNull(message = "{heroId.not_null}", groups = OnCreate.class)
    private Integer heroId;
}
