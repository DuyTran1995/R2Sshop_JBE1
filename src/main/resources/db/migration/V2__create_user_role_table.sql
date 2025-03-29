CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255) UNIQUE,
    phone      VARCHAR(255),
    enabled    TINYINT(1)   NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_role
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

INSERT INTO roles (name)
VALUES ('OPERATOR');
INSERT INTO roles (name)
VALUES ('ADMIN');
INSERT INTO roles (name)
VALUES ('USER');

INSERT INTO users (first_name, last_name, password, email, phone, enabled)
VALUES ('Operator', 'User', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'operator@example.com',
        '123456789', 1);

INSERT INTO users (first_name, last_name, password, email, phone, enabled)
VALUES ('Admin', 'User', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'admin@example.com',
        '123456789', 1);

INSERT INTO users (first_name, last_name, password, email, phone, enabled)
VALUES ('User', 'User', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'user@example.com',
        '123456789', 1);

# Assign Operator for admin account
INSERT IGNORE INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.email = 'operator@example.com'
  AND r.name = 'OPERATOR';

INSERT IGNORE INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.email = 'admin@example.com'
  AND r.name = 'Admin';

INSERT IGNORE INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.email = 'user@example.com'
  AND r.name = 'User';