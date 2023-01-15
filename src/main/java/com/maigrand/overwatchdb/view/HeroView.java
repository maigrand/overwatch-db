package com.maigrand.overwatchdb.view;

import com.maigrand.overwatchdb.entity.Hero;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class HeroView {

    private final Integer id;

    private final String name;

    private final RoleView role;

    private final Set<WeaponView> weapons;

    private final Set<AbilityView> abilities;

    public HeroView(Hero hero) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.role = new RoleView(hero.getRole());
        this.weapons = hero.getWeapons().stream().map(WeaponView::new).collect(Collectors.toSet());
        this.abilities = hero.getAbilities().stream().map(AbilityView::new).collect(Collectors.toSet());
    }
}
