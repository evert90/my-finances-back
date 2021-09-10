CREATE TABLE financial_record(
    id BIGINT(20) NOT NULL,
    name VARCHAR(300) NOT NULL,
    details TEXT NULL,
    value DECIMAL(15,2) NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE financial_record_tags(
    id_financial_record BIGINT(20) NOT NULL,
    id_tag BIGINT(20) NOT NULL,
    FOREIGN KEY (id_financial_record) REFERENCES financial_record(id),
    FOREIGN KEY (id_tag) REFERENCES tag(id),
    PRIMARY KEY(id_financial_record, id_tag)
);