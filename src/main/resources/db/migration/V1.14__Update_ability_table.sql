INSERT INTO ability (name, aimtype_id, hero_id)
VALUES ('Combat Roll', null, (SELECT h.id FROM hero h WHERE h.name = 'cassidy')),
       ('Magnetic Grenade', (SELECT a.id FROM aimtype a WHERE a.type = 'projectile'),
        (SELECT h.id FROM hero h WHERE h.name = 'cassidy'));