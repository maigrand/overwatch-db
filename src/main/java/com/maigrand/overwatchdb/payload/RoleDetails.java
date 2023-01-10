package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RoleDetails {

    @NotNull(message = "{name.not_null}", groups = OnCreate.class)
    private String name;

    @NotNull(message = "{groupBuff.not_null}", groups = OnCreate.class)
    private String groupBuff;
}
