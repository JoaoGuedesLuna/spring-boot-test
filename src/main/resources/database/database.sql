CREATE DATABASE test_db;

USE test_db;

CREATE TABLE Accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE Products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE Orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    account_id BIGINT NOT NULL
);

CREATE TABLE Items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL
);

ALTER TABLE Orders
ADD CONSTRAINT account_id
FOREIGN KEY (account_id)
REFERENCES Accounts(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Items
ADD CONSTRAINT product_id
FOREIGN KEY (product_id)
REFERENCES Products(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Items
ADD CONSTRAINT order_id
FOREIGN KEY (order_id)
REFERENCES Orders(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

DELIMITER //
CREATE PROCEDURE orderTotalPrice(IN orderId BIGINT)
BEGIN
    SELECT SUM(i.unit_price * i.quantity)
    AS order_total_price
    FROM items i
    WHERE i.order_id = orderId;
END //
DELIMITER ;