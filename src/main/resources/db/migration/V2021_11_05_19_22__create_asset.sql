CREATE TABLE asset(
    id BIGINT(20) NOT NULL,
    name VARCHAR(300) NULL,
    details TEXT NULL,
    initial_value DECIMAL(15,2) NOT NULL,
    end_value DECIMAL(15,2) NULL,
    initial_date DATE NOT NULL,
    end_date DATE NULL,
    type VARCHAR(30) NOT NULL,
    renda_fixa_type VARCHAR(30) NULL,
    renda_fixa_rate_type VARCHAR(30) NULL,
    bank VARCHAR(200) NULL,
    rate DECIMAL(5,2) NULL,
    liquidez TINYINT(1) NULL,
    user_id BIGINT(20) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE asset_tags(
    id_asset BIGINT(20) NOT NULL,
    id_tag BIGINT(20) NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES asset(id),
    FOREIGN KEY (id_tag) REFERENCES tag(id),
    PRIMARY KEY(id_asset, id_tag)
);