CREATE TABLE admin_user
(
    id       INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    active   BOOLEAN                                          NOT NULL,
    email    TEXT                                             NOT NULL UNIQUE,
    password TEXT                                             NOT NULL
);