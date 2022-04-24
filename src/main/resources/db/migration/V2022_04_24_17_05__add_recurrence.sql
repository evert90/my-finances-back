CREATE TABLE financial_record_recurrence(
    id BIGINT(20) NOT NULL,
    name VARCHAR(300) NOT NULL,
    details TEXT NULL,
    value DECIMAL(15,2) NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(30) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    paid TINYINT(1) NULL,
    period VARCHAR(30) NOT NULL,
    period_quantity INTEGER NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE financial_record_tags_recurrence(
    id_financial_record_recurrence BIGINT(20) NOT NULL,
    id_tag BIGINT(20) NOT NULL,
    FOREIGN KEY (id_financial_record_recurrence) REFERENCES financial_record_recurrence(id),
    FOREIGN KEY (id_tag) REFERENCES tag(id),
    PRIMARY KEY(id_financial_record_recurrence, id_tag)
);