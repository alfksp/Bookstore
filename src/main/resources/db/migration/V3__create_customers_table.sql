use bookstore;
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);