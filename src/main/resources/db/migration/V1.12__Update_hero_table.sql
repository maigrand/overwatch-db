INSERT INTO hero (name, role_id)
VALUES ('cassidy', (select r.id from role r where r.name = 'dps'));