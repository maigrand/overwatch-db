INSERT INTO weapon (name, usage, damage_min, damage_max, falloff_range_min, falloff_range_max, spread_angle,
                    rate_of_fire, ammo, reload_time, headshot, description, blocked_by_barriers,
                    aimtype_id, hero_id)
VALUES ('Peacekeeper', 'Primary', 21, 70, 20, 40, 'Pinpoint', '1 shot every .5 seconds', 6, 1.5, true,
        'Accurate, powerful revolver.', true,
        (SELECT a.id FROM aimtype a WHERE a.type = 'hitscan'),
        (SELECT h.id FROM HERO h WHERE h.name = 'cassidy'));