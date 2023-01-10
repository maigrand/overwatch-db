package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AimTypeDetails {

    @NotNull(message = "{type.not_null}", groups = OnCreate.class)
    private String type;
}
