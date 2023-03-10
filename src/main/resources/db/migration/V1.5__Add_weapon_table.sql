CREATE TABLE weapon
(
    id                  INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name                VARCHAR(255)                             NOT NULL,
    usage               VARCHAR(255)                             NOT NULL,
    damage_min          FLOAT                                    NOT NULL,
    damage_max          FLOAT                                    NOT NULL,
    falloff_range_min   INTEGER                                  NOT NULL,
    falloff_range_max   INTEGER                                  NOT NULL,
    spread_angle        VARCHAR(255),
    rate_of_fire        VARCHAR(255)                             NOT NULL,
    ammo                INTEGER                                  NOT NULL,
    reload_time         FLOAT                                    NOT NULL,
    headshot            BOOLEAN                                  NOT NULL,
    description         VARCHAR(255),
    blocked_by_barriers BOOLEAN                                  NOT NULL,
    aimtype_id          INTEGER,
    hero_id             INTEGER                                  NOT NULL,
    CONSTRAINT pk_weapon PRIMARY KEY (id)
);

ALTER TABLE weapon
    ADD CONSTRAINT FK_WEAPON_ON_AIMTYPE FOREIGN KEY (aimtype_id) REFERENCES aimtype (id);

ALTER TABLE weapon
    ADD CONSTRAINT FK_WEAPON_ON_HERO FOREIGN KEY (hero_id) REFERENCES hero (id);