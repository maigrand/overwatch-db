package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AimTypeDetails {

    @NotNull(message = "{type.not_null}", groups = OnCreate.class)
    private String type;
}
