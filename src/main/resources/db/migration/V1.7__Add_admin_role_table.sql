CREATE TABLE admin_role
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_admin_role PRIMARY KEY (id)
);