use bookstore;
    CREATE TABLE IF NOT EXISTS cart(
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        customer_id BIGINT NOT NULL,
        book_id BIGINT NOT NULL
    );