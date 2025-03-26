CREATE TABLE IF NOT EXISTS categories
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)   NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    description    TEXT,
    stock_quantity INTEGER DEFAULT 0,
    categories_id BIGINT NULL,
    FOREIGN KEY (categories_id) REFERENCES categories (id)
);


INSERT INTO categories (id, name, description)
VALUES (1, 'Electronics', 'All electronic products');

INSERT INTO categories (id, name, description)
VALUES (2, 'Computers', 'Computers and accessories'),
       (3, 'Phones', 'Mobile phones and accessories');

INSERT INTO categories (id, name, description)
VALUES (4, 'Laptops', 'Portable computers'),
       (5, 'Desktops', 'Desktop computers');

INSERT INTO categories (id, name, description)
VALUES (6, 'Smartphones', 'Smart mobile phones');


INSERT INTO products (name, price, description, stock_quantity)
VALUES ('Smartphone', 599.99, 'A high-end smartphone', 100),
       ('Laptop', 1299.99, 'Gaming laptop with high performance', 50),
       ('Headphones', 99.99, 'Noise-cancelling headphones', 200);

