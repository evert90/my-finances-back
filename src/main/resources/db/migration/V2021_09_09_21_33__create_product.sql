CREATE TABLE product(
    id BIGINT(20) NOT NULL,
    name VARCHAR(300) NOT NULL,
    details TEXT NULL,
    user_id BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE product_categories(
    id_product BIGINT(20) NOT NULL,
    id_category BIGINT(20) NOT NULL,
    FOREIGN KEY (id_category) REFERENCES product_category(id),
    FOREIGN KEY (id_product) REFERENCES product(id),
    PRIMARY KEY(id_product, id_category)
);