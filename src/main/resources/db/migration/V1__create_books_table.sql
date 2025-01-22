use bookstore;
CREATE TABLE IF NOT EXISTS books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    year INTEGER CHECK (year > 1900),
    price DECIMAL(10,2),
    booksAvailable INTEGER DEFAULT 0
    );