MERGE INTO role (id, name)
    KEY (id)
    VALUES (1, 'ROLE_USER');

MERGE INTO role (id, name)
    KEY (id)
    VALUES (2, 'ROLE_MANAGER');

MERGE INTO role (id, name)
    KEY (id)
    VALUES (3, 'ROLE_ADMIN');
