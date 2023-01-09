package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.validator.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AimTypeDetails {

    @NotNull(message = "{type.not_null}", groups = OnCreate.class)
    private String type;
}
