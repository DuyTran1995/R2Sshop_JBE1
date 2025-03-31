CREATE TABLE carts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cart_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

INSERT INTO products (name, price, description, stock_quantity, categories_id)
VALUES ('Laptop Dell XPS', 1500.00, 'Laptop cao cấp', 10, 1),
       ('iPhone 14', 999.00, 'Điện thoại thông minh', 20, 2),
       ('Tai nghe Sony', 150.00, 'Tai nghe không dây', 50, 3);

INSERT INTO carts (user_id)
VALUES (1),
       (2);

INSERT INTO cart_items (cart_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 3, 3);