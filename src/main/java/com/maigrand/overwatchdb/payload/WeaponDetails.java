package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeaponDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{aimTypeId.not_null}", groups = OnCreate.class)
    private Integer aimTypeId;

    @NotNull(message = "{heroId.not_null}", groups = OnCreate.class)
    private Integer heroId;

}
