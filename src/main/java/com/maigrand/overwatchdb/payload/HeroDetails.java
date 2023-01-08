package com.maigrand.overwatchdb.payload;

import com.maigrand.overwatchdb.entity.Role;
import lombok.Data;

@Data
public class HeroDetails {

    private String name;
    private Role role;
}
