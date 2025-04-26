use bookstore;
ALTER TABLE books
ADD COLUMN is_returned BOOLEAN DEFAULT FALSE;