INSERT INTO weapon (name, aimtype_id, hero_id)
VALUES ('Peacekeeper', (SELECT a.id FROM aimtype a WHERE a.type = 'hitscan'),
        (SELECT h.id FROM HERO h WHERE h.name = 'cassidy'));