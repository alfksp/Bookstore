USE bookstore;
CREATE TABLE IF NOT EXISTS customers_roles(
    customer_id BIGINT NOT NULL,
    role_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (customer_id, role_name),
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    CONSTRAINT fk_role_name FOREIGN KEY (role_name) REFERENCES roles(name) ON DELETE CASCADE

);