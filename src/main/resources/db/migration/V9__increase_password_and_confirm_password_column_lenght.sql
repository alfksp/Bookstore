USE bookstore;
ALTER TABLE customers
MODIFY COLUMN password VARCHAR(300),
MODIFY COLUMN confirm_password VARCHAR(300);