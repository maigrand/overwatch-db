INSERT INTO weapon (name, usage, damage, falloff_range, spread_angle, rate_of_fire, ammo, reload_time, headshot,
                    aimtype_id, hero_id)
VALUES ('Peacekeeper', 'Primary', '21 - 70', '20 to 40 meters', 'Pinpoint', '1 shot every .5 seconds', 6, 1.5, true,
        (SELECT a.id FROM aimtype a WHERE a.type = 'hitscan'),
        (SELECT h.id FROM HERO h WHERE h.name = 'cassidy'));