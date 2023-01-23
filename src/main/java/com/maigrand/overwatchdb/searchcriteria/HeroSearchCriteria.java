package com.maigrand.overwatchdb.searchcriteria;

import com.maigrand.overwatchdb.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HeroSearchCriteria {

    private Integer id;

    private String name;

    private Role role;
}
