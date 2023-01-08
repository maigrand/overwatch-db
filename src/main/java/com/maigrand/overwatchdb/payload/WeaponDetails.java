package com.maigrand.overwatchdb.payload;

import lombok.Data;

@Data
public class WeaponDetails {

    private String name;
    private Long aimTypeId;
    private Long heroId;

}
