package com.maigrand.overwatchdb.searchcriteria;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WeaponSearchCriteria {

    private Integer id;

    private String name;

    private String usage;

    private Boolean headshot;
}
