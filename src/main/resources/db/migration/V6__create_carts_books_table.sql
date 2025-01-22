USE bookstore;
CREATE TABLE IF NOT EXISTS carts_books(
    cart_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    PRIMARY KEY (cart_id, book_id),
    CONSTRAINT fk_cart_id FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);
