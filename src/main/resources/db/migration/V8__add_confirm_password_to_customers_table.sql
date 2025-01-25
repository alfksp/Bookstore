USE bookstore;
ALTER TABLE customers
ADD COLUMN confirm_password VARCHAR(50) NOT NULL;
