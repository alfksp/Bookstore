use bookstore;
CREATE TABLE IF NOT EXISTS carts(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
  CONSTRAINT fk_customers_id FOREIGN KEY (customer_id) REFERENCES customers(id)
);