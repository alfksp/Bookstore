use bookstore;
CREATE TABLE IF NOT EXISTS reservations_books(
    reservation_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    PRIMARY KEY (reservation_id, book_id),
    CONSTRAINT fk_reservation_id FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    CONSTRAINT fk_books_id FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE

);