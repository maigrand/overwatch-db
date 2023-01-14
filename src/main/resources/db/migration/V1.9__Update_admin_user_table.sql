ALTER TABLE admin_user
    ADD admin_role_id INTEGER;

ALTER TABLE admin_user
    ADD CONSTRAINT FK_ADMIN_USER_ON_ADMIN_ROLE FOREIGN KEY (admin_role_id) REFERENCES admin_role (id);

UPDATE admin_user SET admin_role_id = 1 WHERE admin_user.email = 'test@test.com';