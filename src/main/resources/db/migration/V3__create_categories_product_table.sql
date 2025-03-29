CREATE TABLE IF NOT EXISTS categories
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)   NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    description    TEXT,
    stock_quantity INTEGER DEFAULT 0,
    categories_id  BIGINT         NULL,
    FOREIGN KEY (categories_id) REFERENCES categories (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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


INSERT INTO products (name, price, description, stock_quantity, categories_id)
VALUES ('Smartphone X1', 599.99, 'A high-end smartphone with advanced features', 100, 1),
       ('Laptop Pro 15', 1299.99, 'Powerful laptop for gaming and work', 50, 2),
       ('Wireless Headphones', 99.99, 'Noise-cancelling headphones', 200, 1),
       ('Smartwatch Z', 199.99, 'Fitness tracker with heart rate monitor', 150, 3),
       ('Tablet Air', 399.99, 'Lightweight tablet for entertainment', 80, 2),
       ('Gaming Mouse', 49.99, 'Ergonomic mouse with RGB lighting', 300, 4),
       ('Mechanical Keyboard', 89.99, 'RGB mechanical keyboard for gaming', 120, 4),
       ('4K Monitor', 349.99, 'Ultra HD monitor for professionals', 60, 2),
       ('Portable Speaker', 79.99, 'Bluetooth speaker with deep bass', 250, 1),
       ('External SSD 1TB', 129.99, 'Fast and reliable storage solution', 90, 5),
       ('Action Camera', 149.99, 'Waterproof camera for adventure', 70, 3),
       ('Wireless Charger', 29.99, 'Fast charging pad for devices', 400, 1),
       ('Gaming Console', 499.99, 'Next-gen gaming console', 30, 4),
       ('Smart TV 55"', 699.99, '4K smart TV with streaming apps', 40, 2),
       ('Fitness Band', 39.99, 'Basic fitness tracker', 500, 3),
       ('Desktop PC', 999.99, 'High-performance PC for work', 25, 5),
       ('Bluetooth Earbuds', 59.99, 'True wireless earbuds', 180, 1),
       ('VR Headset', 299.99, 'Virtual reality headset for gaming', 45, 4),
       ('Mini Projector', 199.99, 'Portable projector for home theater', 65, 2),
       ('USB-C Hub', 34.99, 'Multi-port adapter for laptops', 350, 5);

INSERT INTO products (name, price, description, stock_quantity, categories_id) VALUES
-- Danh mục Electronics (category_id = 1)
('TV 4K 55-inch', 799.99, 'Ultra HD Smart TV with HDR', 30, 1),
('Bluetooth Speaker', 49.99, 'Portable speaker with 10-hour battery', 150, 1),
('Smart Watch', 199.99, 'Fitness tracker with heart rate monitor', 80, 1),
('Wireless Earbuds', 89.99, 'Noise-cancelling earbuds', 200, 1),
('Home Theater System', 399.99, '5.1 surround sound system', 25, 1),
('Digital Camera', 499.99, '20MP camera with 4K video', 40, 1),
('LED Projector', 299.99, '1080p projector for home entertainment', 35, 1),
('Gaming Console', 399.99, 'Next-gen gaming console', 60, 1),
('Smart Light Bulb', 19.99, 'Wi-Fi enabled LED bulb', 300, 1),
('Electric Kettle', 29.99, '1.7L stainless steel kettle', 120, 1),

-- Danh mục Phones (category_id = 2)
('iPhone 14', 999.99, 'Latest iPhone with A16 chip', 50, 2),
('Samsung Galaxy S23', 849.99, 'Flagship phone with 108MP camera', 70, 2),
('Google Pixel 7', 599.99, 'AI-powered smartphone', 60, 2),
('OnePlus 11', 749.99, 'Fast-charging flagship phone', 45, 2),
('Xiaomi 13 Pro', 699.99, 'High-performance phone with Leica camera', 55, 2),
('Nokia G60', 299.99, 'Affordable 5G phone', 90, 2),
('Oppo Find X5', 799.99, 'Premium phone with fast charging', 40, 2),
('Vivo V27', 499.99, 'Stylish phone with AMOLED display', 65, 2),
('Sony Xperia 1 IV', 1199.99, '4K display smartphone', 30, 2),
('Realme GT 2', 549.99, 'Budget-friendly flagship killer', 75, 2),
('Motorola Edge 40', 649.99, 'Curved OLED display phone', 50, 2),
('Huawei P60', 899.99, 'Photography-focused smartphone', 35, 2),
('Asus ROG Phone 7', 999.99, 'Gaming phone with 165Hz display', 25, 2),
('Poco F5', 399.99, 'Mid-range phone with Snapdragon 7+ Gen 2', 80, 2),
('Redmi Note 12', 349.99, 'Affordable phone with 120Hz AMOLED', 100, 2),

-- Danh mục Computers (category_id = 3)
('Dell XPS 13', 1299.99, 'Ultrabook with 13-inch 4K display', 20, 3),
('MacBook Pro 16', 2499.99, 'M2 Max chip, 16-inch Retina display', 15, 3),
('HP Spectre x360', 1399.99, 'Convertible laptop with OLED screen', 25, 3),
('Lenovo ThinkPad X1', 1599.99, 'Business laptop with 14-inch display', 30, 3),
('Asus ZenBook 14', 999.99, 'Lightweight laptop with Ryzen 7', 40, 3),
('Acer Predator Helios', 1799.99, 'Gaming laptop with RTX 4070', 20, 3),
('Surface Laptop 5', 1299.99, '13.5-inch touchscreen laptop', 25, 3),
('Razer Blade 15', 2199.99, 'Gaming laptop with 240Hz display', 15, 3),
('MSI Stealth GS66', 1999.99, 'Slim gaming laptop with RTX 3080', 18, 3),
('LG Gram 17', 1699.99, 'Ultra-light 17-inch laptop', 22, 3),
('Desktop PC Ryzen 5', 799.99, 'Mid-range PC with Ryzen 5 5600X', 35, 3),
('Gaming PC RTX 4090', 2999.99, 'High-end PC with RTX 4090', 10, 3),
('Mini PC Intel NUC', 499.99, 'Compact PC for office use', 50, 3),
('Workstation HP Z4', 2499.99, 'Powerful workstation for professionals', 12, 3),
('All-in-One iMac', 1799.99, '24-inch iMac with M1 chip', 20, 3),
('Chromebook Acer', 299.99, 'Lightweight Chromebook for students', 60, 3),
('Laptop Dell G15', 1099.99, 'Budget gaming laptop with RTX 3050', 30, 3),
('Surface Pro 9', 1299.99, 'Tablet-laptop hybrid with Intel i7', 25, 3),
('ThinkCentre M70', 699.99, 'Compact desktop for business', 40, 3),
('Alienware Aurora R15', 3499.99, 'Premium gaming PC with liquid cooling', 8, 3),
('Mac Mini M2', 799.99, 'Compact desktop with M2 chip', 35, 3),
('Asus TUF Gaming', 1399.99, 'Gaming laptop with Ryzen 9', 20, 3),
('HP Pavilion 15', 849.99, 'All-purpose laptop with Ryzen 5', 45, 3),
('Lenovo Legion 5', 1299.99, 'Gaming laptop with RTX 3060', 25, 3),
('Custom PC Intel i9', 2599.99, 'High-performance PC with i9-13900K', 15, 3);